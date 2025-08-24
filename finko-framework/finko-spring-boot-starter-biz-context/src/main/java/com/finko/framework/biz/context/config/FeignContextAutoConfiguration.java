package com.finko.framework.biz.context.config;

import com.finko.framework.biz.context.interceptor.FeignRequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @title FeignContextAutoConfiguration
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/23 22:12
 * @description Feign 请求拦截器自动配置
 */
@AutoConfiguration
public class FeignContextAutoConfiguration {

    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
