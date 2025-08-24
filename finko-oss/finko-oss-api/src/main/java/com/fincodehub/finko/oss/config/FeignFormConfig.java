package com.fincodehub.finko.oss.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title FeignFormConfig
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/23 21:06
 * @description <TODO description class purpose>
 */
@Configuration
public class FeignFormConfig {

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }
}
