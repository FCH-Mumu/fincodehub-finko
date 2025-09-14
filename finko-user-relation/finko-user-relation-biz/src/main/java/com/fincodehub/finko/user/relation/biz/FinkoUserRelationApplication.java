package com.fincodehub.finko.user.relation.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @title FinkoUserRelationApplication
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/13 19:18
 * @description <TODO description class purpose>
 */
@SpringBootApplication
@MapperScan("com.fincodehub.finko.user.relation.biz.domain.mapper")
@EnableFeignClients(basePackages = "com.fincodehub.finko")
public class FinkoUserRelationApplication {
    public static void main(String[] args){
        SpringApplication.run(FinkoUserRelationApplication.class, args);
    }
}
