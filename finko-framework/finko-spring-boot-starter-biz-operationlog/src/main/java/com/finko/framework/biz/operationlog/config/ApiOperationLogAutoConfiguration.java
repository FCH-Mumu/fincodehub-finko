package com.finko.framework.biz.operationlog.config;

import com.finko.framework.biz.operationlog.aspect.ApiOperationLogAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @title ApiOperationLogAutoConfiguration
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/8 21:29
 * @description <TODO description class purpose>
 */
@AutoConfiguration
public class ApiOperationLogAutoConfiguration {

    @Bean
    public ApiOperationLogAspect apiOperationLogAspect() {
        return new ApiOperationLogAspect();
    }
}