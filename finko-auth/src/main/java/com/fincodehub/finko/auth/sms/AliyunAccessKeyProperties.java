package com.fincodehub.finko.auth.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @title AliyunAccessKeyProperties
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 15:02
 * @description <TODO description class purpose>
 */
@ConfigurationProperties(prefix = "aliyun")
@Component
@Data
public class AliyunAccessKeyProperties {
    private String accessKeyId;
    private String accessKeySecret;
}