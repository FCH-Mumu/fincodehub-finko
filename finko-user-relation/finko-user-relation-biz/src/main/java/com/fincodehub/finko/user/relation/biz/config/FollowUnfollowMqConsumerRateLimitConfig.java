package com.fincodehub.finko.user.relation.biz.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title FollowUnfollowMqConsumerRateLimitConfig
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/14 10:30
 * @description: 关注、取关消费者令牌桶配置类
 **/
@Configuration
@RefreshScope
public class FollowUnfollowMqConsumerRateLimitConfig {

    @Value("${mq-consumer.follow-unfollow.rate-limit}")
    private double rateLimit;

    @Bean
    @RefreshScope
    public RateLimiter rateLimiter() {
        return RateLimiter.create(rateLimit);
    }
}