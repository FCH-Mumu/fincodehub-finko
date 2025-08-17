package com.fincodehub.finko.auth.controller;

import com.fincodehub.finko.auth.model.vo.user.UpdatePasswordReqVO;
import com.fincodehub.finko.auth.model.vo.user.UserLoginReqVO;
import com.fincodehub.finko.auth.service.IUserDOService;
import com.finko.framework.biz.operationlog.aspect.ApiOperationLog;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/login")
    @ApiOperationLog(description = "用户登录/注册")
    public ResponseObject<String> loginAndRegister(@Validated @RequestBody UserLoginReqVO userLoginReqVO) {
        return userService.loginAndRegister(userLoginReqVO);
    }

    @PostMapping("/logout")
    @ApiOperationLog(description = "账号登出")
    public ResponseObject<?> logout() {

        return userService.logout();
    }

    /**
     * 修改密码
     */
    @PostMapping("/password/update")
    @ApiOperationLog(description = "修改密码")
    public ResponseObject<?> updatePassword(@Validated @RequestBody UpdatePasswordReqVO updatePasswordReqVO) {
        return userService.updatePassword(updatePasswordReqVO);
    }

    public static void main(String[] args) {

        // 初始化 InheritableThreadLocal
        ThreadLocal<Long> threadLocal = new InheritableThreadLocal<>();

        // 假设用户 ID 为 1
        Long userId = 1L;

        // 设置用户 ID 到 InheritableThreadLocal 中
        threadLocal.set(userId);

        System.out.println("主线程打印用户 ID: " + threadLocal.get());

        // 异步线程
        new Thread(() -> {
            System.out.println("异步线程打印用户 ID: " + threadLocal.get());
        }).start();
    }



}
