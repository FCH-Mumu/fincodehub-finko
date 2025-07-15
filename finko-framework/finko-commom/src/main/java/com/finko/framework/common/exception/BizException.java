package com.finko.framework.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @title BizException
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/8 20:12
 * @description <TODO description class purpose>
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}