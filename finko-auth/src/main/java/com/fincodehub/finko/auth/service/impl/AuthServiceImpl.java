package com.fincodehub.finko.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.nacos.shaded.com.google.common.base.Preconditions;
import com.fincodehub.finko.auth.constant.RedisKeyConstants;
import com.fincodehub.finko.auth.enums.LoginTypeEnum;
import com.fincodehub.finko.auth.enums.ResponseCodeEnum;
import com.fincodehub.finko.auth.model.vo.user.UpdatePasswordReqVO;
import com.fincodehub.finko.auth.model.vo.user.UserLoginReqVO;
import com.fincodehub.finko.auth.rpc.UserRpcService;
import com.fincodehub.finko.auth.service.AuthService;
import com.fincodehub.finko.user.dto.resp.FindUserByPhoneRspDTO;
import com.finko.framework.biz.context.holder.LoginUserContextHolder;
import com.finko.framework.common.exception.BizException;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户表 服务实现类
 *
 * @author FCH丨木木
 * @since 2025-07-13
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
  @Resource private RedisTemplate<String, Object> redisTemplate;
  
  @Resource(name = "taskExecutor")
  private ThreadPoolTaskExecutor threadPoolTaskExecutor;
  @Resource
  private PasswordEncoder passwordEncoder;

  @Resource
  private UserRpcService userRpcService;

  /**
   * 登录与注册
   *
   * @param userLoginReqVO
   * @return
   */
  @Override
  public ResponseObject<String> loginAndRegister(UserLoginReqVO userLoginReqVO) {
    String phone = userLoginReqVO.getPhone();
    Integer type = userLoginReqVO.getType();

    LoginTypeEnum loginTypeEnum = LoginTypeEnum.valueOf(type);

    if (Objects.isNull(loginTypeEnum)){
      throw new BizException(ResponseCodeEnum.LOGIN_TYPE_ERROR);
    }
    Long userId = null;

    // 判断登录类型
    switch (loginTypeEnum) {
      // 验证码登录
      case VERIFICATION_CODE:
        String verificationCode = userLoginReqVO.getCode();

        // 校验入参验证码是否为空
        // if (StringUtils.isBlank(verificationCode)) {
        //     return ResponseObject.fail(ResponseCodeEnum.PARAM_NOT_VALID.getErrorCode(),
        // "验证码不能为空");
        // }
        // 校验入参验证码是否为空 Guava
        log.info("==> verificationCode: {}", verificationCode);
        Preconditions.checkArgument(StringUtils.isNoneBlank(verificationCode), "验证码不能为空");
        log.info("==> 11111111111111111111");

        // 构建验证码 Redis Key
        String key = RedisKeyConstants.buildVerificationCodeKey(phone);
        // 查询存储在 Redis 中该用户的登录验证码
        String sentCode = (String) redisTemplate.opsForValue().get(key);

        System.out.println("key======" + key);
        System.out.println("sentCode======" + sentCode);
        System.out.println("verificationCode======" + verificationCode);
        // 判断用户提交的验证码，与 Redis 中的验证码是否一致
        if (!StringUtils.equals(verificationCode, sentCode)) {
          throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_ERROR);
        }

        // // 通过手机号查询记录
        // UserDO userDO = userDOMapper.selectByPhone(phone);
        //
        // log.info("==> 用户是否注册, phone: {}, userDO: {}", phone, JsonUtils.toJsonString(userDO));
        //
        // // 判断是否注册
        // if (Objects.isNull(userDO)) {
        //   // 若此用户还没有注册，系统自动注册该用户
        //   userId = registerUser(phone);
        //
        // } else {
        //   // 已注册，则获取其用户 ID
        //   userId = userDO.getId();
        // }

        // RPC: 调用用户服务，注册用户
        Long userIdTmp = userRpcService.registerUser(phone);

        // 若调用用户服务，返回的用户 ID 为空，则提示登录失败
        if (Objects.isNull(userIdTmp)) {
          throw new BizException(ResponseCodeEnum.LOGIN_FAIL);
        }

        userId = userIdTmp;

        break;
      // 密码登录
      case PASSWORD:// 密码登录
        String password = userLoginReqVO.getPassword();
        // 根据手机号查询
        // UserDO userDO1 = userDOMapper.selectByPhone(phone);
        FindUserByPhoneRspDTO findUserByPhoneRspDTO = userRpcService.findUserByPhone(phone);

        // 判断该手机号是否注册
        if (Objects.isNull(findUserByPhoneRspDTO)) {
          throw new BizException(ResponseCodeEnum.USER_NOT_FOUND);
        }

        // 拿到密文密码
        String encodePassword = findUserByPhoneRspDTO.getPassword();

        // 匹配密码是否一致
        boolean isPasswordCorrect = passwordEncoder.matches(password, encodePassword);

        // 如果不正确，则抛出业务异常，提示用户名或者密码不正确
        if (!isPasswordCorrect) {
          throw new BizException(ResponseCodeEnum.PHONE_OR_PASSWORD_ERROR);
        }

        userId = findUserByPhoneRspDTO.getId();
        break;
      default:
        break;
    }

    // SaToken 登录用户，并返回 token 令牌
    // SaToken 登录用户, 入参为用户 ID
    System.out.println("==> userId: " + userId);
    StpUtil.login(userId);

    // 获取 Token 令牌
    SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
    log.info("==> 登录 tokenInfo: {}", tokenInfo);
    long loginIdAsLong = StpUtil.getLoginIdAsLong();
    log.info("==> 登录用户 Long ID: {}", loginIdAsLong);
 
    // 返回 Token 令牌
    return ResponseObject.success(tokenInfo.tokenValue);
  }

  /**
   * 退出登录
   * @return com.finko.framework.common.response.ResponseObject<?>
   */
  @Override
  public ResponseObject<?> logout() {
    Long userId = LoginUserContextHolder.getUserId();
    log.info("==> 用户退出登录, userId: {}", userId);

    threadPoolTaskExecutor.submit(() -> {
      Long userId2 = LoginUserContextHolder.getUserId();
      log.info("==> 异步线程中获取 userId: {}", userId2);
    });


    // 退出登录 指定用户 ID
    StpUtil.logout(userId);

    return  ResponseObject.success();
  }


  /**
   * 修改密码
   * @param updatePasswordReqVO
   * @return
   */
  @Override
  public ResponseObject<String> updatePassword(UpdatePasswordReqVO updatePasswordReqVO) {
    // 新密码
    String newPassword = updatePasswordReqVO.getNewPassword();
    // 密码加密
    String encodePassword = passwordEncoder.encode(newPassword);
    // 获取对应的用户ID
    Long userId = LoginUserContextHolder.getUserId();
    // UserDO userDO = UserDO.builder()
    //         .id(userId)
    //         .password(encodePassword)
    //         .updateTime(LocalDateTime.now())
    //         .build();
    // // 更新密码
    // userDOMapper.updateByPrimaryKeySelective(userDO);
    // rpc 调用
    userRpcService.updatePassword(encodePassword);

    return ResponseObject.success();
  }
}
