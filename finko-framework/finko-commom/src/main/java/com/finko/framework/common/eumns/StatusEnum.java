package com.finko.framework.common.eumns;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title StatusEnum
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 14:42
 * @description <TODO description class purpose>
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    // 启用
    ENABLE(0),
    // 禁用
    DISABLED(1);

    private final Integer value;
}