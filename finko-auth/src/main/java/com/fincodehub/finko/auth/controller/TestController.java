package com.fincodehub.finko.auth.controller;

import com.fincodehub.finko.auth.alarm.AlarmInterface;
import com.fincodehub.finko.auth.config.RateLimitConfig;
import com.fincodehub.finko.auth.domain.UserDO;
import com.finko.framework.biz.operationlog.aspect.ApiOperationLog;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @GetMapping("/tr")
    public void tr(){

        redisTemplate.opsForValue().set("tttt", "yyyyy");
    }

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
    public ResponseObject<UserDO> test2(@RequestBody  @Validated UserDO user) {
        int i = 10 / 0;

        return ResponseObject.success(user);
    }



    private final RateLimitConfig rateLimitConfig;

    public TestController(RateLimitConfig rateLimitConfig) {
        this.rateLimitConfig = rateLimitConfig;
    }

    @GetMapping("/testl")
    public String testLimit() {
        // return "当前限流阈值为: " + limit;
     
        return "当前限流阈值为: " +rateLimitConfig.getLimit();
    }



    @Resource
    private
    AlarmInterface alarm;


    @GetMapping("/alarm")
    public
    String sendAlarm() {
        alarm.send(
                "系统出错啦，finko这个月绩效没了，速度上线解决问题！"
        );
        return "alarm success"
                ;
    }

}