package com.fincodehub.finko.note.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @title FinkoNoteBizApplication
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/6 10:44
 * @description <TODO description class purpose>
 */
@SpringBootApplication
@MapperScan("com.fincodehub.finko.note.biz.domain.mapper")
@EnableFeignClients(basePackages = "com.fincodehub.finko")
public class FinkoNoteBizApplication {
    public static void main(String[] args){
        SpringApplication.run(FinkoNoteBizApplication.class, args);
    }
}
