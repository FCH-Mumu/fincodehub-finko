package com.fincodehub.finko.user.biz.runner;

import com.fincodehub.finko.user.biz.constant.RedisKeyConstants;
import jakarta.annotation.Resource;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @title DelRolePemission2RedisRunner
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/14 20:38
 * @description <TODO description class purpose>
 */
@Component
@Slf4j
public class DelRolePemission2RedisRunner implements SmartLifecycle {
  private final AtomicBoolean running = new AtomicBoolean(false);

  @Resource private RedisTemplate redisTemplate;

  @Override
  public void start() {
    log.info("==> start...");
    running.set(true);
  }

  @Override
  public void stop() {
    log.info("==> stop...");

    if (running.get()) {
      try {
        boolean canPushed = redisTemplate.hasKey(RedisKeyConstants.buildPushPermissionFlagKey());
        if (canPushed) {
          // 删除 RedisKeyConstants.buildPushPermissionFlagKey()
          redisTemplate.delete(RedisKeyConstants.buildPushPermissionFlagKey());
          log.info("==> 服务关闭，角色权限数据已删除...");
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        running.set(false);
      }
    }
  }

  @Override
  public boolean isRunning() {
    log.info("==> isRunning...");
    return running.get();
  }

  @Override
  public int getPhase() {
    // 在绝大多数组件之后停止，但在 Redis ConnectionFactory 停止之前
      // 数值越小越早执行
    return Integer.MAX_VALUE - 10;
  }
}
