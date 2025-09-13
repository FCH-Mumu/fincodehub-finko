package com.fincodehub.finko.note.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fincodehub.finko.note.biz.constant.MQConstants;
import com.fincodehub.finko.note.biz.constant.RedisKeyConstants;
import com.fincodehub.finko.note.biz.domain.dataobject.Note;
import com.fincodehub.finko.note.biz.domain.mapper.NoteMapper;
import com.fincodehub.finko.note.biz.domain.mapper.TopicMapper;
import com.fincodehub.finko.note.biz.enums.NoteStatusEnum;
import com.fincodehub.finko.note.biz.enums.NoteTypeEnum;
import com.fincodehub.finko.note.biz.enums.NoteVisibleEnum;
import com.fincodehub.finko.note.biz.enums.ResponseCodeEnum;
import com.fincodehub.finko.note.biz.model.vo.DeleteNoteReqVO;
import com.fincodehub.finko.note.biz.model.vo.FindNoteDetailReqVO;
import com.fincodehub.finko.note.biz.model.vo.FindNoteDetailRspVO;
import com.fincodehub.finko.note.biz.model.vo.PublishNoteReqVO;
import com.fincodehub.finko.note.biz.model.vo.TopNoteReqVO;
import com.fincodehub.finko.note.biz.model.vo.UpdateNoteReqVO;
import com.fincodehub.finko.note.biz.model.vo.UpdateNoteVisibleOnlyMeReqVO;
import com.fincodehub.finko.note.biz.rpc.DistributedIdGeneratorRpcService;
import com.fincodehub.finko.note.biz.rpc.KeyValueRpcService;
import com.fincodehub.finko.note.biz.rpc.UserRpcService;
import com.fincodehub.finko.note.biz.service.INoteService;
import com.fincodehub.finko.user.dto.resp.FindUserByIdRspDTO;
import com.finko.framework.biz.context.holder.LoginUserContextHolder;
import com.finko.framework.common.exception.BizException;
import com.finko.framework.common.response.ResponseObject;
import com.finko.framework.common.util.JsonUtils;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.base.Preconditions;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 笔记表 服务实现类
 *
 * @author FCH丨木木
 * @since 2025-09-06
 */
@Service
@Slf4j
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements INoteService {
  @Resource private NoteMapper noteMapper;
  @Resource private TopicMapper topicMapper;
  @Resource private DistributedIdGeneratorRpcService distributedIdGeneratorRpcService;
  @Resource private KeyValueRpcService keyValueRpcService;
  @Resource private UserRpcService userRpcService;
  @Resource private RocketMQTemplate rocketMQTemplate;

  // 接口类型，后期可扩展
  @Resource(name = "taskExecutor")
  private Executor threadPoolTaskExecutor;

  @Resource private RedisTemplate<String, String> redisTemplate;

  /** 笔记详情本地缓存 */
  private static final Cache<Long, String> LOCAL_CACHE =
      Caffeine.newBuilder()
          .initialCapacity(10000) // 设置初始容量为 10000 个条目
          .maximumSize(10000) // 设置缓存的最大容量为 10000 个条目
          .expireAfterWrite(1, TimeUnit.HOURS) // 设置缓存条目在写入后 1 小时过期
          .build();

  @Override
  public ResponseObject<?> publishNote(PublishNoteReqVO publishNoteReqVO) {
    // 笔记类型
    Integer type = publishNoteReqVO.getType();
    // 获取对应类型的枚举
    NoteTypeEnum noteTypeEnum = NoteTypeEnum.valueOf(type);
    // 若非图文、视频、抛出业务异常
    if (Objects.isNull(noteTypeEnum)) {
      throw new BizException(ResponseCodeEnum.NOTE_TYPE_ERROR);
    }
    String imgUris = null;
    // 笔记内容是否为空，默认值为true，即空
    Boolean isContentEmpty = true;
    String videoUri = null;
    switch (noteTypeEnum) {
      // 图文笔记
      case IMAGE_TEXT:
        List<String> imgUriList = publishNoteReqVO.getImgUris();
        // 校验图片是否为空
        Preconditions.checkArgument(CollUtil.isNotEmpty(imgUriList), "笔记图片不能为空");
        // 校验图片数量
        Preconditions.checkArgument(imgUriList.size() <= 9, "图片数量不能超过9张");
        // 将图片链接拼接，以逗号隔开
        imgUris = StringUtils.join(imgUriList, ",");
        break;
      // 视频笔记
      case VIDEO:
        videoUri = publishNoteReqVO.getVideoUri();
        // 校验视频链接是否为空
        Preconditions.checkArgument(StringUtils.isNoneBlank(videoUri), "视频链接不能为空");
        break;
      default:
        break;
    }

    // RPC:调用分布式ID生成服务，生成笔记ID
    String snowflakeId = distributedIdGeneratorRpcService.getSnowflakeId();
    // 笔记内容UUID
    String contentUuid = null;

    // 笔记内容
    String content = publishNoteReqVO.getContent();
    // 若用户填写笔记内容
    if (StringUtils.isNotBlank(content)) {
      // 内容是否为空，置为false，即不为空
      isContentEmpty = false;
      // 生成笔记内容UUID
      contentUuid = UUID.randomUUID().toString();
      // RPC:调用KV键值服务，存储短文本
      boolean isSavedSuccess = keyValueRpcService.saveNoteContent(contentUuid, content);

      // 若存储失败，抛出业务异常，提示用户发布笔记失败
      if (!isSavedSuccess) {
        throw new BizException(ResponseCodeEnum.NOTE_PUBLISH_FAIL);
      }
    }
    // 话题
    Long topicId = publishNoteReqVO.getTopicId();
    String topicName = null;
    if (Objects.nonNull(topicId)) {
      // 获取话题名称
      topicName = topicMapper.selectNameByPrimaryKey(topicId);
    }
    // 发布者用户ID
    Long creatorId = LoginUserContextHolder.getUserId();
    // 构建笔记Do对象
    Note note =
        Note.builder()
            .id(Long.valueOf(snowflakeId))
            .isContentEmpty(isContentEmpty)
            .creatorId(creatorId)
            .imgUris(imgUris)
            .title(publishNoteReqVO.getTitle())
            .topicId(publishNoteReqVO.getTopicId())
            .topicName(topicName)
            .type(type)
            .visible(NoteVisibleEnum.PUBLIC.getCode())
            .createTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .status(NoteStatusEnum.NORMAL.getCode())
            .isTop(Boolean.FALSE)
            .videoUri(videoUri)
            .contentUuid(contentUuid)
            .build();

    try {
      // 笔记入库存储
      noteMapper.insert(note);
    } catch (Exception e) {
      log.error("===> 笔记存储失败", e);
      // Rpc:笔记保存失败，删除笔记内容
      if (StringUtils.isNotBlank(contentUuid)) {
        keyValueRpcService.deleteNoteContent(contentUuid);
      }
    }
    return ResponseObject.success();
  }

  @Override
  @SneakyThrows
  public ResponseObject<FindNoteDetailRspVO> findNoteDetail(
      FindNoteDetailReqVO findNoteDetailReqVO) {
    // 查询的笔记 ID
    Long noteId = findNoteDetailReqVO.getId();

    // 当前登录用户
    Long userId = LoginUserContextHolder.getUserId();

    // 先从本地缓存中查询
    String findNoteDetailRspVOStrLocalCache = LOCAL_CACHE.getIfPresent(noteId);
    if (StringUtils.isNotBlank(findNoteDetailRspVOStrLocalCache)) {
      FindNoteDetailRspVO findNoteDetailRspVO =
          JsonUtils.parseObject(findNoteDetailRspVOStrLocalCache, FindNoteDetailRspVO.class);
      log.info("==> 命中了本地缓存；{}", findNoteDetailRspVOStrLocalCache);
      // 可见性校验
      checkNoteVisibleFromVO(userId, findNoteDetailRspVO);
      return ResponseObject.success(findNoteDetailRspVO);
    }

    // 从Redis缓存中获取
    String noteDetailRedisKey = RedisKeyConstants.buildNoteDetailKey(noteId);
    String noteDetailJson = redisTemplate.opsForValue().get(noteDetailRedisKey);

    // 若缓存中有该笔记的数据，则直接返回
    if (StringUtils.isNotBlank(noteDetailJson)) {
      FindNoteDetailRspVO findNoteDetailRspVO =
          JsonUtils.parseObject(noteDetailJson, FindNoteDetailRspVO.class);
      // 异步线程中将用户信息存入本地缓存
      threadPoolTaskExecutor.execute(
          () -> {
            // 写入本地缓存
            LOCAL_CACHE.put(
                noteId,
                Objects.isNull(findNoteDetailRspVO)
                    ? "null"
                    : JsonUtils.toJsonString(findNoteDetailRspVO));
          });
      // 可见性校验
      checkNoteVisibleFromVO(userId, findNoteDetailRspVO);
      return ResponseObject.success(findNoteDetailRspVO);
    }

    // 若Redis缓存中获取不到，则走数据库查询
    // 查询笔记
    Note noteDO = noteMapper.selectByPrimaryKey(noteId);

    // 若该笔记不存在，则抛出业务异常
    if (Objects.isNull(noteDO)) {
      threadPoolTaskExecutor.execute(
          () -> {
            // 防止缓存穿透，将不存在的笔记缓存起来（过期时间不宜设置过长）
            // 保底1分钟+随机秒数
            long expireSeconds = 60 + RandomUtil.randomInt(60);
            redisTemplate
                .opsForValue()
                .set(noteDetailRedisKey, "null", expireSeconds, TimeUnit.SECONDS);
          });
      throw new BizException(ResponseCodeEnum.NOTE_NOT_FOUND);
    }

    // 可见性校验
    Integer visible = noteDO.getVisible();
    checkNoteVisible(visible, userId, noteDO.getCreatorId());

    // 并发查询优化
    // RPC: 调用用户服务
    Long creatorId = noteDO.getCreatorId();
    log.info("调用用户服务，查询用户信息，用户 ID：{}", creatorId);
    // FindUserByIdRspDTO findUserByIdRspDTO = userRpcService.findById(creatorId);
    CompletableFuture<FindUserByIdRspDTO> userResultFuture =
        CompletableFuture.supplyAsync(
            () -> userRpcService.findById(creatorId), threadPoolTaskExecutor);

    // RPC: 调用 K-V 存储服务获取内容
    // String content = null;
    // if (Objects.equals(noteDO.getIsContentEmpty(), Boolean.FALSE)) {
    //     content = keyValueRpcService.findNoteContent(noteDO.getContentUuid());
    // }
    CompletableFuture<String> contentResultFuture = CompletableFuture.completedFuture(null);
    if (Objects.equals(noteDO.getIsContentEmpty(), Boolean.FALSE)) {
      contentResultFuture =
          CompletableFuture.supplyAsync(
              () -> keyValueRpcService.findNoteContent(noteDO.getContentUuid()),
              threadPoolTaskExecutor);
    }

    CompletableFuture<String> finalContentResultFuture = contentResultFuture;
    CompletableFuture<FindNoteDetailRspVO> resultFuture =
        CompletableFuture.allOf(userResultFuture, contentResultFuture)
            .thenApply(
                s -> {
                  // 获取Future返回的结果
                  FindUserByIdRspDTO findUserByIdRspDTO = userResultFuture.join();
                  String content = finalContentResultFuture.join();
                  // 笔记类型
                  // 笔记类型
                  Integer noteType = noteDO.getType();
                  // 图文笔记图片链接(字符串)
                  String imgUrisStr = noteDO.getImgUris();
                  // 图文笔记图片链接(集合)
                  List<String> imgUris = null;
                  // 如果查询的是图文笔记，需要将图片链接的逗号分隔开，转换成集合
                  if (Objects.equals(noteType, NoteTypeEnum.IMAGE_TEXT.getCode())
                      && StringUtils.isNotBlank(imgUrisStr)) {
                    imgUris = List.of(imgUrisStr.split(","));
                  }
                  // 构建返参 VO 实体类
                  return FindNoteDetailRspVO.builder()
                      .id(noteDO.getId())
                      .type(noteDO.getType())
                      .title(noteDO.getTitle())
                      .content(content)
                      .imgUris(imgUris)
                      .topicId(noteDO.getTopicId())
                      .topicName(noteDO.getTopicName())
                      .creatorId(noteDO.getCreatorId())
                      .creatorName(findUserByIdRspDTO.getNickName())
                      .avatar(findUserByIdRspDTO.getAvatar())
                      .videoUri(noteDO.getVideoUri())
                      .updateTime(noteDO.getUpdateTime())
                      .visible(noteDO.getVisible())
                      .build();
                });

    // 获取拼装后的 FindNoteDetailRspVO
    FindNoteDetailRspVO findNoteDetailRspVO = resultFuture.get();

    // 异步线程中将笔记详情存入redis
    threadPoolTaskExecutor.execute(
        () -> {
          String noteDetailJson1 = JsonUtils.toJsonString(findNoteDetailRspVO);
          // 过期时间（保底1天 + 随机秒数，将缓存过期时间打散，防止同一时间大量缓存失效，导致数据库压力太大）
          long expireSeconds = 60 * 60 * 24 + RandomUtil.randomInt(60 * 60 * 24);
          redisTemplate
              .opsForValue()
              .set(noteDetailRedisKey, noteDetailJson1, expireSeconds, TimeUnit.SECONDS);
        });
    return ResponseObject.success(findNoteDetailRspVO);
  }

  /**
   * 校验笔记的可见性
   *
   * @param visible 是否可见
   * @param currUserId 当前用户 ID
   * @param creatorId 笔记创建者
   */
  private void checkNoteVisible(Integer visible, Long currUserId, Long creatorId) {
    if (Objects.equals(visible, NoteVisibleEnum.PRIVATE.getCode())
        && !Objects.equals(currUserId, creatorId)) { // 仅自己可见, 并且访问用户为笔记创建者才能访问，非本人则抛出异常
      throw new BizException(ResponseCodeEnum.NOTE_PRIVATE);
    }
  }

  /**
   * 校验笔记的可见性（针对 VO 实体类）
   *
   * @param userId
   * @param findNoteDetailRspVO
   */
  private void checkNoteVisibleFromVO(Long userId, FindNoteDetailRspVO findNoteDetailRspVO) {
    if (Objects.nonNull(findNoteDetailRspVO)) {
      Integer visible = findNoteDetailRspVO.getVisible();
      checkNoteVisible(visible, userId, findNoteDetailRspVO.getCreatorId());
    }
  }

  /**
   * 笔记更新
   *
   * @param updateNoteReqVO
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public ResponseObject<?> updateNote(UpdateNoteReqVO updateNoteReqVO) {
    // 笔记 ID
    Long noteId = updateNoteReqVO.getId();
    // 笔记类型
    Integer type = updateNoteReqVO.getType();

    // 获取对应类型的枚举
    NoteTypeEnum noteTypeEnum = NoteTypeEnum.valueOf(type);

    // 若非图文、视频，抛出业务业务异常
    if (Objects.isNull(noteTypeEnum)) {
      throw new BizException(ResponseCodeEnum.NOTE_TYPE_ERROR);
    }

    String imgUris = null;
    String videoUri = null;
    switch (noteTypeEnum) {
      case IMAGE_TEXT: // 图文笔记
        List<String> imgUriList = updateNoteReqVO.getImgUris();
        // 校验图片是否为空
        Preconditions.checkArgument(CollUtil.isNotEmpty(imgUriList), "笔记图片不能为空");
        // 校验图片数量
        Preconditions.checkArgument(imgUriList.size() <= 8, "笔记图片不能多于 8 张");

        imgUris = StringUtils.join(imgUriList, ",");
        break;
      case VIDEO: // 视频笔记
        videoUri = updateNoteReqVO.getVideoUri();
        // 校验视频链接是否为空
        Preconditions.checkArgument(StringUtils.isNotBlank(videoUri), "笔记视频不能为空");
        break;
      default:
        break;
    }

    // 话题
    Long topicId = updateNoteReqVO.getTopicId();
    String topicName = null;
    if (Objects.nonNull(topicId)) {
      topicName = topicMapper.selectNameByPrimaryKey(topicId);

      // 判断一下提交的话题, 是否是真实存在的
      if (StringUtils.isBlank(topicName)) {
        throw new BizException(ResponseCodeEnum.TOPIC_NOT_FOUND);
      }
    }

    // 删除Redis缓存
    String noteDetailRedisKey = RedisKeyConstants.buildNoteDetailKey(noteId);
    redisTemplate.delete(noteDetailRedisKey);

    // 更新笔记元数据表 sys_note
    String content = updateNoteReqVO.getContent();
    Note noteDO =
        Note.builder()
            .id(noteId)
            .isContentEmpty(StringUtils.isBlank(content))
            .imgUris(imgUris)
            .title(updateNoteReqVO.getTitle())
            .topicId(updateNoteReqVO.getTopicId())
            .topicName(topicName)
            .type(type)
            .updateTime(LocalDateTime.now())
            .videoUri(videoUri)
            .build();

    noteMapper.updateById(noteDO);

    // // 删除 Redis 缓存
    // String noteDetailRedisKey = RedisKeyConstants.buildNoteDetailKey(noteId);
    // redisTemplate.delete(noteDetailRedisKey);

    // 一致性保证：延迟双删
    // redisTemplate.delete(noteDetailRedisKey);
    // 异步发送延时消息
    Message<String> message = MessageBuilder.withPayload(String.valueOf(noteId)).build();
    rocketMQTemplate.asyncSend(
        MQConstants.TOPIC_DELAY_DELETE_NOTE_REDIS_CACHE,
        message,
        new SendCallback() {
          @Override
          public void onSuccess(SendResult sendResult) {
            log.info("==> 延时删除Redis 笔记缓存消息发送成功...");
          }

          @Override
          public void onException(Throwable throwable) {
            log.info("==> 延时删除Redis 笔记缓存消息发送失败...");
          }
        },
        3000, // 超时时间(毫秒)
        1 // 延迟级别，1 表示延时 1s
        );

    // 删除本地缓存
    // LOCAL_CACHE.invalidate(noteId);
    rocketMQTemplate.syncSend(MQConstants.TOPIC_DELETE_NOTE_LOCAL_CACHE, noteId);
    log.info("==>MQ：删除笔记本地缓存发送成功...");

    // 笔记内容更新
    // 查询此篇笔记内容对应的 UUID
    Note noteDO1 = noteMapper.selectByPrimaryKey(noteId);
    String contentUuid = noteDO1.getContentUuid();

    // 笔记内容是否更新成功
    boolean isUpdateContentSuccess = false;
    if (StringUtils.isBlank(content)) {
      // 若笔记内容为空，则删除 K-V 存储
      isUpdateContentSuccess = keyValueRpcService.deleteNoteContent(contentUuid);
    } else {
      // 若将无内容的笔记，更新为了有内容的笔记，需要重新生成 UUID
      contentUuid = StringUtils.isBlank(contentUuid) ? UUID.randomUUID().toString() : contentUuid;
      // 调用 K-V 更新短文本
      isUpdateContentSuccess = keyValueRpcService.saveNoteContent(contentUuid, content);
    }

    // 如果更新失败，抛出业务异常，回滚事务
    if (!isUpdateContentSuccess) {
      throw new BizException(ResponseCodeEnum.NOTE_UPDATE_FAIL);
    }

    return ResponseObject.success();
  }

  /**
   * 删除本地笔记缓存
   *
   * @param noteId
   */
  @Override
  public void deleteNoteLocalCache(Long noteId) {
    LOCAL_CACHE.invalidate(noteId);
  }


  /**
   * 删除笔记
   *
   * @param deleteNoteReqVO
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public ResponseObject<?> deleteNote(DeleteNoteReqVO deleteNoteReqVO) {
    // 笔记 ID
    Long noteId = deleteNoteReqVO.getId();

    // 逻辑删除
    Note noteDO = Note.builder()
            .id(noteId)
            .status(NoteStatusEnum.DELETED.getCode())
            .updateTime(LocalDateTime.now())
            .build();

    // MyBatis-Plus 的 updateById 默认就是选择性更新（非 null 字段）
    int count = noteMapper.updateById(noteDO);
    // 若影响的行数为 0，则表示该笔记不存在
    if (count == 0) {
      throw new BizException(ResponseCodeEnum.NOTE_NOT_FOUND);
    }

    // 删除缓存
    String noteDetailRedisKey = RedisKeyConstants.buildNoteDetailKey(noteId);
    redisTemplate.delete(noteDetailRedisKey);

    // 同步发送广播模式 MQ，将所有实例中的本地缓存都删除掉
    rocketMQTemplate.syncSend(MQConstants.TOPIC_DELETE_NOTE_LOCAL_CACHE, noteId);
    log.info("====> MQ：删除笔记本地缓存发送成功...");

    return ResponseObject.success();
  }

  /**
   * 笔记仅对自己可见
   *
   * @param updateNoteVisibleOnlyMeReqVO
   * @return
   */
  @Override
  public ResponseObject<?> visibleOnlyMe(UpdateNoteVisibleOnlyMeReqVO updateNoteVisibleOnlyMeReqVO) {
    // 笔记 ID
    Long noteId = updateNoteVisibleOnlyMeReqVO.getId();

    // 构建更新 DO 实体类
    Note noteDO = Note.builder()
            .id(noteId)
            .visible(NoteVisibleEnum.PRIVATE.getCode()) // 可见性设置为仅对自己可见
            .updateTime(LocalDateTime.now())
            .build();

    // 执行更新 SQL
    int count = noteMapper.updateVisibleOnlyMe(noteDO);

    // 若影响的行数为 0，则表示该笔记无法修改为仅自己可见
    if (count == 0) {
      throw new BizException(ResponseCodeEnum.NOTE_CANT_VISIBLE_ONLY_ME);
    }

    // 删除 Redis 缓存
    String noteDetailRedisKey = RedisKeyConstants.buildNoteDetailKey(noteId);
    redisTemplate.delete(noteDetailRedisKey);

    // 同步发送广播模式 MQ，将所有实例中的本地缓存都删除掉
    rocketMQTemplate.syncSend(MQConstants.TOPIC_DELETE_NOTE_LOCAL_CACHE, noteId);
    log.info("====> MQ：删除笔记本地缓存发送成功...");

    return ResponseObject.success();
  }

  /**
   * 笔记置顶 / 取消置顶
   *
   * @param topNoteReqVO
   * @return
   */
  @Override
  public ResponseObject<?> topNote(TopNoteReqVO topNoteReqVO) {
    // 笔记 ID
    Long noteId = topNoteReqVO.getId();
    // 是否置顶
    Boolean isTop = topNoteReqVO.getIsTop();

    // 当前登录用户 ID
    Long currUserId = LoginUserContextHolder.getUserId();

    // 构建置顶/取消置顶 DO 实体类
    Note noteDO = Note.builder()
            .id(noteId)
            .isTop(isTop)
            .updateTime(LocalDateTime.now())
            .creatorId(currUserId) // 只有笔记所有者，才能置顶/取消置顶笔记
            .build();

    int count = noteMapper.updateIsTop(noteDO);

    if (count == 0) {
      throw new BizException(ResponseCodeEnum.NOTE_CANT_OPERATE);
    }

    // 删除 Redis 缓存
    String noteDetailRedisKey = RedisKeyConstants.buildNoteDetailKey(noteId);
    redisTemplate.delete(noteDetailRedisKey);

    // 同步发送广播模式 MQ，将所有实例中的本地缓存都删除掉
    rocketMQTemplate.syncSend(MQConstants.TOPIC_DELETE_NOTE_LOCAL_CACHE, noteId);
    log.info("====> MQ：删除笔记本地缓存发送成功...");

    return ResponseObject.success();
  }


}
