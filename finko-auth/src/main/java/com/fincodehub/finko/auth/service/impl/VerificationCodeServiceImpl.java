package com.fincodehub.finko.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.fincodehub.finko.auth.constant.RedisKeyConstants;
import com.fincodehub.finko.auth.enums.ResponseCodeEnum;
import com.fincodehub.finko.auth.model.vo.veriticationcode.SendVerificationCodeReqVO;
import com.fincodehub.finko.auth.service.VerificationCodeService;
import com.fincodehub.finko.auth.sms.AliyunSmsHelper;
import com.finko.framework.common.exception.BizException;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @title VerificationCodeService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/12 21:56
 * @description <TODO description class purpose>
 */
@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService {

  @Resource
  private RedisTemplate<String, Object> redisTemplate;
  @Resource
  private ThreadPoolTaskExecutor threadPoolTaskExecutor;
  @Resource
  private AliyunSmsHelper aliyunSmsHelper;
  /**
   * 发送短信验证码
   *
   * @param sendVerificationCodeReqVO
   * @return
   */
  @Override
  public ResponseObject<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO) {
    // 手机号
    String phone = sendVerificationCodeReqVO.getPhone();

    // 构建验证码 redis key
    String key = RedisKeyConstants.buildVerificationCodeKey(phone);

    // 判断是否已发送验证码
    boolean isSent = redisTemplate.hasKey(key);
    if (isSent) {
      // 若之前发送的验证码未过期，则提示发送频繁
      throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_SEND_FREQUENTLY);
    }

    // 生成 6 位随机数字验证码
    String verificationCode = RandomUtil.randomNumbers(6);

    // todo: 调用第三方短信发送服务

    log.info("==> 手机号: {}, 已发送验证码：【{}】", phone, verificationCode);

    // 调用第三方短信发送服务
    threadPoolTaskExecutor.submit(() -> {
      String signName = "阿里云短信测试";
      String templateCode = "SMS_154950909";
      String templateParam = String.format("{\"code\":\"%s\"}", verificationCode);
      aliyunSmsHelper.sendMessage(signName, templateCode, phone, templateParam);
    });

    // 存储验证码到 redis, 并设置过期时间为 3 分钟
    redisTemplate.opsForValue().set(key, verificationCode, 3, TimeUnit.MINUTES);

    return ResponseObject.success();
  }
}