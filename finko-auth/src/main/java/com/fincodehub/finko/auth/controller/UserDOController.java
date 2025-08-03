package com.fincodehub.finko.auth.controller;

import com.fincodehub.finko.auth.model.vo.user.UserLoginReqVO;
import com.fincodehub.finko.auth.service.IUserDOService;
import com.finko.framework.biz.operationlog.aspect.ApiOperationLog;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-07-27
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserDOController {

    @Resource
    private IUserDOService userService;

    @PostMapping("/login")
    @ApiOperationLog(description = "用户登录/注册")
    public ResponseObject<String> loginAndRegister(@Validated @RequestBody UserLoginReqVO userLoginReqVO) {
        return userService.loginAndRegister(userLoginReqVO);
    }

    @PostMapping("/logout")
    @ApiOperationLog(description = "账号登出")
    public ResponseObject<?> logout() {

        // todo 账号退出登录逻辑待实现

        return ResponseObject.success();
    }

}
