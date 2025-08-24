package com.fincodehub.finko.oss.biz.controller;

import com.finko.framework.biz.operationlog.aspect.ApiOperationLog;
import com.finko.framework.common.response.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title TestFeignController
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/23 16:25
 * @description Feign 测试接口
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class TestFeignController {

    @PostMapping(value = "/test")
    @ApiOperationLog(description = "Feign 测试接口")
    public ResponseObject<?> test() {
        return ResponseObject.success();
    }

}
