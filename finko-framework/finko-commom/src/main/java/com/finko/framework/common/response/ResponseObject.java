package com.finko.framework.common.response;

import com.finko.framework.common.exception.BaseExceptionInterface;
import com.finko.framework.common.exception.BizException;
import lombok.Data;

import java.io.Serializable;

/**
 * @title ResponseObjectObject
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/8 17:04
 * @description <TODO description class purpose>
 */
@Data
public class ResponseObject <T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean success = true;
    private String errorCode;
    private String message;
    private T data;

    public static <T> ResponseObject<T> success() {
        return new ResponseObject<>();
    }

    public static <T> ResponseObject<T> success(T data) {
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setData(data);
        return responseObject;
    }

    public static <T> ResponseObject<T> fail() {
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setSuccess(false);
        return responseObject;
    }

    public static <T> ResponseObject<T> fail(String errorMessage) {
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setSuccess(false);
        responseObject.setMessage(errorMessage);
        return responseObject;
    }

    public static <T> ResponseObject<T> fail(String errorCode, String errorMessage) {
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setSuccess(false);
        responseObject.setErrorCode(errorCode);
        responseObject.setMessage(errorMessage);
        return responseObject;
    }

    public static <T> ResponseObject<T> fail(BizException bizException) {
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setSuccess(false);
        responseObject.setErrorCode(bizException.getErrorCode());
        responseObject.setMessage(bizException.getErrorMessage());
        return responseObject;
    }

    public static <T> ResponseObject<T> fail(BaseExceptionInterface baseExceptionInterface) {
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setSuccess(false);
        responseObject.setErrorCode(baseExceptionInterface.getErrorCode());
        responseObject.setMessage(baseExceptionInterface.getErrorMessage());
        return responseObject;
    }


}
