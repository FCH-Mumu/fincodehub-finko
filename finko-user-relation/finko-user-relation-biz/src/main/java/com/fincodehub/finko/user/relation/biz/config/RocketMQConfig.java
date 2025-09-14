package com.fincodehub.finko.user.relation.biz.config;

import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @title RocketMQConfig
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/14 8:32
 * @description <TODO description class purpose>
 */
@Configuration
@Import(RocketMQAutoConfiguration.class)
public class RocketMQConfig {
}