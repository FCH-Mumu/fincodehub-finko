package com.fincodehub.finko.auth.config;

import com.alibaba.cloud.nacos.annotation.NacosConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @title RateLimitConfig
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/26 22:56
 * @description <TODO description class purpose>
 */
@Configuration
@RefreshScope
public class RateLimitConfig {

    @NacosConfig(dataId = "finko-auth", group = "DEFAULT_GROUP", key = "rate-limit.api.limit")
    private Integer limit;

    // @NacosValue(value = "${rate-limit.api.limit}", autoRefreshed = true)
    // private Integer limit;

    

    public Integer getLimit() {
        return limit;
    }
}