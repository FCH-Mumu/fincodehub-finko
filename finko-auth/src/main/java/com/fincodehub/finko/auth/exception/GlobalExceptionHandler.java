package com.fincodehub.finko.auth.exception;

import com.fincodehub.finko.auth.enums.ResponseCodeEnum;
import com.finko.framework.common.exception.BizException;
import com.finko.framework.common.response.ResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @title GlobalExceptionHandler
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/12 13:59
 * @description <TODO description class purpose>
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获自定义业务异常
     * @return
     */
    @ExceptionHandler({ BizException.class })
    @ResponseBody
    public ResponseObject<Object> handleBizException(HttpServletRequest request, BizException e) {
        log.warn("{} request fail, errorCode: {}, errorMessage: {}", request.getRequestURI(), e.getErrorCode(), e.getErrorMessage());
        return ResponseObject.fail(e);
    }

    /**
     * 捕获参数校验异常
     * @return
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseBody
    public ResponseObject<Object> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        // 参数错误异常码
        String errorCode = ResponseCodeEnum.PARAM_NOT_VALID.getErrorCode();

        // 获取 BindingResult
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder sb = new StringBuilder();

        // 获取校验不通过的字段，并组合错误信息，格式为： email 邮箱格式不正确, 当前值: '123124qq.com';
        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(errors -> {
            errors.forEach(error ->
                    sb.append(error.getField())
                            .append(" ")
                            .append(error.getDefaultMessage())
                            .append(", 当前值: '")
                            .append(error.getRejectedValue())
                            .append("'; ")

            );
        });

        // 错误信息
        String errorMessage = sb.toString();

        log.warn("{} request error, errorCode: {}, errorMessage: {}", request.getRequestURI(), errorCode, errorMessage);

        return ResponseObject.fail(errorCode, errorMessage);
    }

    /**
     * 其他类型异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ResponseObject<Object> handleOtherException(HttpServletRequest request, Exception e) {
        log.error("{} request error, ", request.getRequestURI(), e);
        return ResponseObject.fail(ResponseCodeEnum.SYSTEM_ERROR);
    }
}