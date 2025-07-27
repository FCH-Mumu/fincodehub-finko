package com.finko.framework.common.eumns;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title DeletedEnum
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 14:42
 * @description <TODO description class purpose>
 */
@Getter
@AllArgsConstructor
public enum DeletedEnum {

    YES(true),
    NO(false);

    private final Boolean value;
}
