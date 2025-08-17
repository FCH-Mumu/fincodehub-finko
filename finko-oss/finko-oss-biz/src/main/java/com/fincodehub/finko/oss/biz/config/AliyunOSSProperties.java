package com.fincodehub.finko.oss.biz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @title MinioProperties
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/17 12:57
 * @description <TODO description class purpose>
 */
@ConfigurationProperties(prefix = "storage.aliyun-oss")
@Component
@Data
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String accessKey;
    private String secretKey;
}