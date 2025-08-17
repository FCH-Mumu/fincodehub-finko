package com.fincodehub.finko.oss.biz.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.Protocol;
import com.aliyun.oss.common.comm.SignVersion;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title AliyunOSSConfig
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/17 13:56
 * @description 阿里云 Client 配置
 */
@Slf4j
@Configuration
public class AliyunOSSConfig {

    @Resource
    private AliyunOSSProperties aliyunOSSProperties;

    /**
     * 构建 阿里云 OSS 客户端
     *
     * @return
     */
    @Bean
    public OSS aliyunOSSClient() {
        log.info("==> 创建阿里云 OSS Client{}",aliyunOSSProperties.getAccessKey());
        log.info("==> 创建阿里云 OSS Client{}",aliyunOSSProperties.getSecretKey());
        // 设置访问凭证
        DefaultCredentialProvider credentialsProvider = CredentialsProviderFactory.newDefaultCredentialProvider(
                aliyunOSSProperties.getAccessKey(), aliyunOSSProperties.getSecretKey());


        // 创建 ClientBuilderConfiguration 实例，用于配置 OSS 客户端参数
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        // 设置签名算法版本为 V4
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        // 设置使用 HTTPS 协议访问 OSS，保证传输安全性
        clientBuilderConfiguration.setProtocol(Protocol.HTTPS);

        // 创建 OSS 客户端实例
        OSS ossClient = OSSClientBuilder.create()
                // 以华东1（杭州）地域的外网访问域名为例，Endpoint填写为oss-cn-hangzhou.aliyuncs.com
                .endpoint(aliyunOSSProperties.getEndpoint())
                // 从环境变量中获取访问凭证（需提前配置 OSS_ACCESS_KEY_ID 和 OSS_ACCESS_KEY_SECRET）
                .credentialsProvider(credentialsProvider)
                // 设置客户端配置
                .clientConfiguration(clientBuilderConfiguration)
                // 以华东1（杭州）地域为例，Region填写为cn-hangzhou
                .region("cn-beijing")
                .build();

        // 创建 OSSClient 实例
        // return new OSSClientBuilder().build(aliyunOSSProperties.getEndpoint(), credentialsProvider);
        return ossClient;
    }
}
