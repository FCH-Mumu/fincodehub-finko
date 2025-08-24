package com.fincodehub.finko.user.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @title FinkoUserBizApplication
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/17 19:06
 * @description <TODO description class purpose>
 */
@SpringBootApplication
@MapperScan("com.fincodehub.finko.user.biz.mapper")
@EnableFeignClients(basePackages = "com.fincodehub.finko")
public class FinkoUserBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinkoUserBizApplication.class, args);
    }
}
