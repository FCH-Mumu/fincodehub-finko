package com.fincodehub.finko.auth.controller;

import com.fincodehub.finko.auth.model.vo.veriticationcode.SendVerificationCodeReqVO;
import com.fincodehub.finko.auth.service.VerificationCodeService;
import com.finko.framework.biz.operationlog.aspect.ApiOperationLog;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title VerificationCodeController
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 14:50
 * @description <TODO description class purpose>
 */
@RestController
@Slf4j
public class VerificationCodeController {

    @Resource
    private VerificationCodeService verificationCodeService;

    @PostMapping("/verification/code/send")
    @ApiOperationLog(description = "发送短信验证码")
    public ResponseObject<?> send(@Validated @RequestBody SendVerificationCodeReqVO sendVerificationCodeReqVO) {
        return verificationCodeService.send(sendVerificationCodeReqVO);
    }

}