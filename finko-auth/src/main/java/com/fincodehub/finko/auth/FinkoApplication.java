package com.fincodehub.finko.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @title FinkoApplication
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/8 15:43
 * @description <TODO description class purpose>
 */
@SpringBootApplication
@MapperScan("com.fincodehub.finko.auth.mapper")
public class FinkoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinkoApplication.class, args);
    }
}
