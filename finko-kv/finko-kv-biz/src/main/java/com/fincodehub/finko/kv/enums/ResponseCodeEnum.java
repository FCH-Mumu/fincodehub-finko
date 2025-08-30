package com.fincodehub.finko.kv.enums;

import com.finko.framework.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title ResponseCodeEnum
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/30 21:51
 * @description <TODO description class purpose>
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {
    // ----------------- 通用异常状态码 -----------------
    SYSTEM_ERROR("KV-10000", "出错了，后台小哥正在努力修复中..."),
    PARAM_NOT_VALID("KV-10001", "参数不合法"),

    // ----------------- 业务异常状态码 -----------------
    NOTE_CONTENT_NOT_FOUND("KV-20001", "笔记内容不存在"),
    ;
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;
}
