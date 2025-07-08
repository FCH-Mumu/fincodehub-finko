package com.finko.framework.common.exception;

/**
 * @title BaseExceptionInterface
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/8 20:12
 * @description <TODO description class purpose>
 */
public interface BaseExceptionInterface {
    // 获取异常码
    String getErrorCode();

    // 获取异常信息
    String getErrorMessage();
}
