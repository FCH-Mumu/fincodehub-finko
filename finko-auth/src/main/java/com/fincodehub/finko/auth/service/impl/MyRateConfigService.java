package com.fincodehub.finko.auth.service.impl;


import com.alibaba.cloud.nacos.annotation.NacosConfigListener;
import org.springframework.stereotype.Service;

/**
 * @title MyRateConfigService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/26 23:16
 * @description <TODO description class purpose>
 */
@Service
public class MyRateConfigService {
    @NacosConfigListener(dataId = "finko-auth",group = "DEFAULT_GROUP")
    public void onConfigChange(String config)
    {
        // 解析配置内容（比如 YAML 或 JSON）
        // 更新 limit 字段
        System.out.println("Nacos 配置变更: " + config);
        // 这里可以触发你自己的刷新逻辑
    }
    @NacosConfigListener(dataId = "finko-auth-dev",group = "DEFAULT_GROUP")
    public void finkoDev(String config)
    {
        // 解析配置内容（比如 YAML 或 JSON）
        // 更新 limit 字段
        System.out.println("Nacos 配置变更: " + config);
        // 这里可以触发你自己的刷新逻辑
    }
}
