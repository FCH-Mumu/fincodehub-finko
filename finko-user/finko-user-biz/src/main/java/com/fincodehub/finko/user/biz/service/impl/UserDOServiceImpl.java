package com.fincodehub.finko.user.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fincodehub.finko.user.biz.constant.RedisKeyConstants;
import com.fincodehub.finko.user.biz.constant.RoleConstants;
import com.fincodehub.finko.user.biz.domain.RoleDO;
import com.fincodehub.finko.user.biz.domain.UserDO;
import com.fincodehub.finko.user.biz.domain.UserRoleDO;
import com.fincodehub.finko.user.biz.enums.ResponseCodeEnum;
import com.fincodehub.finko.user.biz.enums.SexEnum;
import com.fincodehub.finko.user.biz.mapper.RoleDOMapper;
import com.fincodehub.finko.user.biz.mapper.UserDOMapper;
import com.fincodehub.finko.user.biz.mapper.UserRoleDOMapper;
import com.fincodehub.finko.user.biz.model.vo.UpdateUserInfoReqVO;
import com.fincodehub.finko.user.biz.rpc.DistributedIdGeneratorRpcService;
import com.fincodehub.finko.user.biz.rpc.OssRpcService;
import com.fincodehub.finko.user.biz.service.IUserDOService;
import com.fincodehub.finko.user.dto.req.FindUserByIdReqDTO;
import com.fincodehub.finko.user.dto.req.FindUserByPhoneReqDTO;
import com.fincodehub.finko.user.dto.req.FindUsersByIdsReqDTO;
import com.fincodehub.finko.user.dto.req.RegisterUserReqDTO;
import com.fincodehub.finko.user.dto.req.UpdateUserPasswordReqDTO;
import com.fincodehub.finko.user.dto.resp.FindUserByIdRspDTO;
import com.fincodehub.finko.user.dto.resp.FindUserByPhoneRspDTO;
import com.finko.framework.biz.context.holder.LoginUserContextHolder;
import com.finko.framework.common.eumns.DeletedEnum;
import com.finko.framework.common.eumns.StatusEnum;
import com.finko.framework.common.exception.BizException;
import com.finko.framework.common.response.ResponseObject;
import com.finko.framework.common.util.JsonUtils;
import com.finko.framework.common.util.ParamUtils;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.base.Preconditions;
import jakarta.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户表 服务实现类
 *
 * @author FCH丨木木
 * @since 2025-08-17
 */
@Service
@Slf4j
public class UserDOServiceImpl extends ServiceImpl<UserDOMapper, UserDO> implements IUserDOService {

    @Resource
    private UserDOMapper userDOMapper;
    @Resource
    private OssRpcService ossRpcService;

    @Resource
    private UserRoleDOMapper userRoleDOMapper;
    @Resource
    private RoleDOMapper roleDOMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private DistributedIdGeneratorRpcService distributedIdGeneratorRpcService;
    
    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 用户信息本地缓存
     */
    private static final Cache<Long, FindUserByIdRspDTO> LOCAL_CACHE = Caffeine.newBuilder()
            .initialCapacity(10000) // 设置初始容量为 10000 个条目
            .maximumSize(10000) // 设置缓存的最大容量为 10000 个条目
            .expireAfterWrite(1, TimeUnit.HOURS) // 设置缓存条目在写入后 1 小时过期
            .build();

    /**
     * 用户注册
     *
     * @param registerUserReqDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseObject<Long> register(RegisterUserReqDTO registerUserReqDTO) {
        String phone = registerUserReqDTO.getPhone();

        // 先判断该手机号是否已被注册
        UserDO userDO1 = userDOMapper.selectByPhone(phone);

        log.info("==> 用户是否注册, phone: {}, userDO: {}", phone, JsonUtils.toJsonString(userDO1));

        // 若已注册，则直接返回用户 ID
        if (Objects.nonNull(userDO1)) {
            return ResponseObject.success(userDO1.getId());
        }

        // 否则注册新用户
        // 获取全局自增finko ID
        // Long finkoId = redisTemplate.opsForValue().increment(RedisKeyConstants.FINKO_ID_GENERATOR_KEY);

        String finkoId = distributedIdGeneratorRpcService.getFinkoId();

        // RPC
        String userIdStr = distributedIdGeneratorRpcService.getUserId();
        Long userId = Long.parseLong(userIdStr);

        UserDO userDO = UserDO.builder()
                .id(userId)
                .phone(phone)
                .finkoId(finkoId) // 自动生成小红书号 ID
                .nickname("小红薯" + finkoId) // 自动生成昵称, 如：小红薯10000
                .status(StatusEnum.ENABLE.getValue()) // 状态为启用
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .isDeleted(DeletedEnum.NO.getValue()) // 逻辑删除
                .build();

        // 添加入库
        userDOMapper.insert(userDO);

        // 获取刚刚添加入库的用户 ID
        // Long userId = userDO.getId();

        // 给该用户分配一个默认角色
        UserRoleDO userRoleDO = UserRoleDO.builder()
                .userId(userId)
                .roleId(RoleConstants.COMMON_USER_ROLE_ID)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .isDeleted(DeletedEnum.NO.getValue())
                .build();
        userRoleDOMapper.insert(userRoleDO);

        RoleDO roleDO = roleDOMapper.selectByPrimaryKey(RoleConstants.COMMON_USER_ROLE_ID);

        // 将该用户的角色 ID 存入 Redis 中
        List<String> roles = new ArrayList<>(1);
        roles.add(roleDO.getRoleKey());

        String userRolesKey = RedisKeyConstants.buildUserRoleKey(userId);
        redisTemplate.opsForValue().set(userRolesKey, JsonUtils.toJsonString(roles));

        return ResponseObject.success(userId);
    }
    /**
     * 根据手机号查询用户信息
     *
     * @param findUserByPhoneReqDTO
     * @return
     */
    @Override
    public ResponseObject<FindUserByPhoneRspDTO> findUserByPhone(FindUserByPhoneReqDTO findUserByPhoneReqDTO) {
        String phone = findUserByPhoneReqDTO.getPhone();

        // 根据手机号查询用户信息
        UserDO userDO = userDOMapper.selectByPhone(phone);

        // 判空
        if (Objects.isNull(userDO)){
            throw new BizException(ResponseCodeEnum.USER_NOT_FOUND);
        }
        // 构建返回结果
        FindUserByPhoneRspDTO findUserByPhoneRspDTO = FindUserByPhoneRspDTO.builder()
                .id(userDO.getId())
                .password(userDO.getPassword())
                .build();
        return ResponseObject.success(findUserByPhoneRspDTO);
    }

    /**
     * 更新用户密码
     *
     * @param updateUserPasswordReqDTO
     * @return
     */
    @Override
    public ResponseObject<?> updatePassword(UpdateUserPasswordReqDTO updateUserPasswordReqDTO) {
        // 获取当前请求对应的用户ID
        Long userId = LoginUserContextHolder.getUserId();
        UserDO userDO = UserDO.builder()
                .id(userId)
                .password(updateUserPasswordReqDTO.getEncodePassword()) // 加密后的密码
                .updateTime(LocalDateTime.now())
                .build();
        // 更新密码
        userDOMapper.updateByPrimaryKeySelective(userDO);
        return ResponseObject.success();
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param findUserByIdReqDTO
     * @return
     */
    @Override
    public ResponseObject<FindUserByIdRspDTO> findById(FindUserByIdReqDTO findUserByIdReqDTO) {
        Long userId = findUserByIdReqDTO.getId();
        // 先从本地缓存中获取
        FindUserByIdRspDTO findUserByIdRspDTOLocalCache = LOCAL_CACHE.getIfPresent(userId);
        if (Objects.nonNull(findUserByIdRspDTOLocalCache)){
            log.info("==> 命中本地缓存：{}", findUserByIdRspDTOLocalCache);
            return ResponseObject.success(findUserByIdRspDTOLocalCache);
        }
        // 用户缓存Redis key
        String userInfoRedisKey = RedisKeyConstants.buildUserInfoKey(userId);

        // 先从Redis缓存中查询
        String userInfoRedisValue = (String) redisTemplate.opsForValue().get(userInfoRedisKey);

        // 若Redis缓存中存在该用户信息，则直接返回
        if(StringUtils.isNotBlank(userInfoRedisValue)){
            // 将存储的json字符串转换成对象
            FindUserByIdRspDTO findUserByIdRspDTO = JsonUtils.parseObject(userInfoRedisValue, FindUserByIdRspDTO.class);
            // 异步线程中将用户信息缓存到本地缓存
            threadPoolTaskExecutor.submit(() -> {
                // 写入本地缓存
                LOCAL_CACHE.put(userId, findUserByIdRspDTO);
            });
            return ResponseObject.success(findUserByIdRspDTO);
        }
        // 否则从数据库查询
        // 根据用户ID查询用户信息
        UserDO userDO = userDOMapper.selectByPrimaryKey(userId);

        // 判空
        if (Objects.isNull(userDO)){
            threadPoolTaskExecutor.execute(() ->{
                // 防止缓存穿透，将数据存储到Redis中（过期时间不宜过长）
                // 保底1分钟+随机秒数
                long expireSeconds = 60 + RandomUtil.randomInt(60);
                redisTemplate.opsForValue().set(userInfoRedisKey, "null", expireSeconds, TimeUnit.SECONDS);
            });
            throw  new BizException(ResponseCodeEnum.USER_NOT_FOUND);
        }

        // 构建返参
        FindUserByIdRspDTO findUserByIdRspDTO = FindUserByIdRspDTO.builder()
                .id(userDO.getId())
                .nickName(userDO.getNickname())
                .avatar(userDO.getAvatar())
                .introduction(userDO.getIntroduction())
                .build();
        // 异步将用户信息存入redis缓存，提升相应速度
        threadPoolTaskExecutor.submit(() -> {
            // 过期时间（保底1天+随机秒数，将缓存过期时间打算，防止同一时间大量存换失效，导致数据库压力太大）
            long expireSeconds = 60*60*24 + RandomUtil.randomInt(60*60*24);
            redisTemplate.opsForValue().set(userInfoRedisKey, JsonUtils.toJsonString(findUserByIdRspDTO), expireSeconds, TimeUnit.SECONDS);
        });
        return ResponseObject.success(findUserByIdRspDTO);
    }

    /**
     * 批量根据用户 ID 查询用户信息
     *
     * @param findUsersByIdsReqDTO
     * @return
     */
    @Override
    public ResponseObject<List<FindUserByIdRspDTO>> findByIds(FindUsersByIdsReqDTO findUsersByIdsReqDTO) {
        // 需要查询的用户 ID 集合
        List<Long> userIds = findUsersByIdsReqDTO.getIds();

        // 构建 Redis Key 集合
        List<String> redisKeys = userIds.stream()
                .map(RedisKeyConstants::buildUserInfoKey)
                .toList();

        // 先从 Redis 缓存中查, multiGet 批量查询提升性能
        List<Object> redisValues = redisTemplate.opsForValue().multiGet(redisKeys);
        // 如果缓存中不为空
        if (CollUtil.isNotEmpty(redisValues)) {
            // 过滤掉为空的数据
            redisValues = redisValues.stream().filter(Objects::nonNull).toList();
        }

        // 返参
        List<FindUserByIdRspDTO> findUserByIdRspDTOS = Lists.newArrayList();

        // 将过滤后的缓存集合，转换为 DTO 返参实体类
        if (CollUtil.isNotEmpty(redisValues)) {
            findUserByIdRspDTOS = redisValues.stream()
                    .map(value -> JsonUtils.parseObject(String.valueOf(value), FindUserByIdRspDTO.class))
                    .collect(Collectors.toList());
        }

        // 如果被查询的用户信息，都在 Redis 缓存中, 则直接返回
        if (CollUtil.size(userIds) == CollUtil.size(findUserByIdRspDTOS)) {
            return ResponseObject.success(findUserByIdRspDTOS);
        }

        // 还有另外两种情况：一种是缓存里没有用户信息数据，还有一种是缓存里数据不全，需要从数据库中补充
        // 筛选出缓存里没有的用户数据，去查数据库
        List<Long> userIdsNeedQuery = null;

        if (CollUtil.isNotEmpty(findUserByIdRspDTOS)) {
            // 将 findUserInfoByIdRspDTOS 集合转 Map
            Map<Long, FindUserByIdRspDTO> map = findUserByIdRspDTOS.stream()
                    .collect(Collectors.toMap(FindUserByIdRspDTO::getId, p -> p));

            // 筛选出需要查 DB 的用户 ID
            userIdsNeedQuery = userIds.stream()
                    .filter(id -> Objects.isNull(map.get(id)))
                    .toList();
        } else { // 缓存中一条用户信息都没查到，则提交的用户 ID 集合都需要查数据库
            userIdsNeedQuery = userIds;
        }

        // 从数据库中批量查询
        List<UserDO> userDOS = userDOMapper.selectByIds(userIdsNeedQuery);

        List<FindUserByIdRspDTO> findUserByIdRspDTOS2 = null;

        // 若数据库查询的记录不为空
        if (CollUtil.isNotEmpty(userDOS)) {
            // DO 转 DTO
            findUserByIdRspDTOS2 = userDOS.stream()
                    .map(userDO -> FindUserByIdRspDTO.builder()
                            .id(userDO.getId())
                            .nickName(userDO.getNickname())
                            .avatar(userDO.getAvatar())
                            .introduction(userDO.getIntroduction())
                            .build())
                    .collect(Collectors.toList());

            // 异步线程将用户信息同步到 Redis 中
            List<FindUserByIdRspDTO> finalFindUserByIdRspDTOS = findUserByIdRspDTOS2;
            threadPoolTaskExecutor.submit(() -> {
                // DTO 集合转 Map
                Map<Long, FindUserByIdRspDTO> map = finalFindUserByIdRspDTOS.stream()
                        .collect(Collectors.toMap(FindUserByIdRspDTO::getId, p -> p));

                // 执行 pipeline 操作
                redisTemplate.executePipelined(new SessionCallback<>() {
                    @Override
                    public Object execute(RedisOperations operations) {
                        for (UserDO userDO : userDOS) {
                            Long userId = userDO.getId();

                            // 用户信息缓存 Redis Key
                            String userInfoRedisKey = RedisKeyConstants.buildUserInfoKey(userId);

                            // DTO 转 JSON 字符串
                            FindUserByIdRspDTO findUserInfoByIdRspDTO = map.get(userId);
                            String value = JsonUtils.toJsonString(findUserInfoByIdRspDTO);

                            // 过期时间（保底1天 + 随机秒数，将缓存过期时间打散，防止同一时间大量缓存失效，导致数据库压力太大）
                            long expireSeconds = 60*60*24 + RandomUtil.randomInt(60*60*24);
                            operations.opsForValue().set(userInfoRedisKey, value, expireSeconds, TimeUnit.SECONDS);
                        }
                        return null;
                    }
                });
            });
        }

        // 合并数据
        if (CollUtil.isNotEmpty(findUserByIdRspDTOS2)) {
            findUserByIdRspDTOS.addAll(findUserByIdRspDTOS2);
        }

        return ResponseObject.success(findUserByIdRspDTOS);
    }

    /**
   * 更新用户信息
   *
   * @param updateUserInfoReqVO
   * @return
   */
  @Override
  public ResponseObject<?> updateUserInfo(UpdateUserInfoReqVO updateUserInfoReqVO) {
    UserDO userDO = new UserDO();
    // 设置当前需要更新的用户Id
    userDO.setId(LoginUserContextHolder.getUserId());
    // 标识位， 是否需要更新
    boolean needUpdate = false;

    // 头像
    MultipartFile avatarFile = updateUserInfoReqVO.getAvatar();
    if (Objects.nonNull(avatarFile)) {
        String avatarUrl = ossRpcService.uploadFile(avatarFile);
        log.info("==============> 调用 oss 服务成功，上传头像，url:{}", avatarUrl);
        // 上传头像失败，抛出异常
        if (StringUtils.isBlank(avatarUrl)){
          throw new BizException(ResponseCodeEnum.UPLOAD_AVATAR_FAIL);
        }
        userDO.setAvatar(avatarUrl);
        needUpdate = true;
    }

    // 昵称
    String nickname = updateUserInfoReqVO.getNickname();
    log.info("==============>nickname{}", nickname);
    if (StringUtils.isNotBlank(nickname)) {
      Preconditions.checkArgument(
          ParamUtils.checkNickname(nickname),
          ResponseCodeEnum.NICK_NAME_VALID_FAIL.getErrorMessage());
      userDO.setNickname(nickname);
      needUpdate = true;
    }
    // finko 号
    String finkoId = updateUserInfoReqVO.getFinkoId();
    if (StringUtils.isNotBlank(finkoId)) {
      Preconditions.checkArgument(
          ParamUtils.checkFinkoId(finkoId), ResponseCodeEnum.FINKO_ID_VALID_FAIL.getErrorMessage());
      userDO.setFinkoId(finkoId);
      needUpdate = true;
    }

    // 性别
    Integer sex = updateUserInfoReqVO.getSex();
    if (Objects.nonNull(sex)) {
      Preconditions.checkArgument(
          SexEnum.isValid(sex), ResponseCodeEnum.SEX_VALID_FAIL.getErrorMessage());
      userDO.setSex(sex);
      needUpdate = true;
    }
    // 生日
    LocalDate birthday = updateUserInfoReqVO.getBirthday();
    if (Objects.nonNull(birthday)) {
      userDO.setBirthday(birthday);
      needUpdate = true;
    }

    // 个人简介
    String introduction = updateUserInfoReqVO.getIntroduction();
    if (StringUtils.isNotBlank(introduction)) {
      Preconditions.checkArgument(
          ParamUtils.checkLength(introduction, 100),
          ResponseCodeEnum.INTRODUCTION_VALID_FAIL.getErrorMessage());
      userDO.setIntroduction(introduction);
      needUpdate = true;
    }

    // 背景图
    MultipartFile backgroundImgFile = updateUserInfoReqVO.getBackgroundImg();
    if (Objects.nonNull(backgroundImgFile)) {
       String backgroundImgUrl = ossRpcService.uploadFile(backgroundImgFile);
       log.info("==============> 调用 oss 服务成功，上传背景图，url:{}", backgroundImgUrl);

       // 上传背景图失败，抛出异常
       if (StringUtils.isBlank(backgroundImgUrl)){
         throw new BizException(ResponseCodeEnum.UPLOAD_BACKGROUND_IMG_FAIL);
       }
       userDO.setBackgroundImg(backgroundImgUrl);
       needUpdate = true;
    }

    if (needUpdate) {
      // 更新用户信息
      userDO.setUpdateTime(LocalDateTime.now());
      userDOMapper.updateByPrimaryKeySelective(userDO);
    }
    return ResponseObject.success();
  }
}
