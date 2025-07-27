package com.fincodehub.finko.auth.service;

import com.fincodehub.finko.auth.model.vo.veriticationcode.SendVerificationCodeReqVO;
import com.finko.framework.common.response.ResponseObject;

/**
 * @title VerificationCodeService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 14:47
 * @description <TODO description class purpose>
 */
public interface VerificationCodeService {
    /**
     * 发送短信验证码
     *
     * @param sendVerificationCodeReqVO
     * @return
     */
    ResponseObject<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO);
}
