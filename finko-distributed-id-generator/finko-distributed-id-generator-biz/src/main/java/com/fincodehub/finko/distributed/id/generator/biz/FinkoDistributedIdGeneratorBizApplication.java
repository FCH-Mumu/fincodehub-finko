package com.fincodehub.finko.distributed.id.generator.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @title FinkoDistributedIdGeneratorBizApplication
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/31 17:48
 * @description <TODO description class purpose>
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FinkoDistributedIdGeneratorBizApplication {
    public static void main(String[] args){
        SpringApplication.run(FinkoDistributedIdGeneratorBizApplication.class, args);
    }
}
