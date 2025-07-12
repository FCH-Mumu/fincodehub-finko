package com.fincodehub.finko.auth.controller;

import com.fincodehub.finko.auth.domain.UserDo;
import com.finko.framework.biz.operationlog.aspect.ApiOperationLog;
import com.finko.framework.common.response.ResponseObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title TestController
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/8 20:35
 * @description <TODO description class purpose>
 */
@RestController
public class TestController {

    @GetMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public ResponseObject<String> test() {
        return ResponseObject.success("Hello, Finko");
    }

    @GetMapping("/test2")
    @ApiOperationLog(description = "测试接口2")
    public ResponseObject test2() {
        return ResponseObject.success();
    }

    @PostMapping("/test3")
    @ApiOperationLog(description = "测试接口3")
    public ResponseObject<UserDo> test2(@RequestBody  @Validated UserDo user) {
        int i = 10 / 0;

        return ResponseObject.success(user);
    }
}