package com.fincodehub.finko.gateway;

import cn.dev33.satoken.context.SaHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @title FinkoGatewayApplication
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/3 12:01
 * @description <TODO description class purpose>
 */
@SpringBootApplication
public class FinkoGatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(FinkoGatewayApplication.class, args);
        // ✅ 正确方式：通过 SaHolder 获取
        cn.dev33.satoken.context.SaTokenContext context = SaHolder.getContext();
         System.out.println("way------"+(context != null ? context.getClass().getSimpleName() : "null"));
    }
}
