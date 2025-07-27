package com.fincodehub.finko.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @title LoginTypeEnum
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 14:12
 * @description <TODO description class purpose>
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
    // 验证码
    VERIFICATION_CODE(1),
    // 密码
    PASSWORD(2);

    private final Integer value;

    public static LoginTypeEnum valueOf(Integer code) {
        for (LoginTypeEnum loginTypeEnum : LoginTypeEnum.values()) {
            if (Objects.equals(code, loginTypeEnum.getValue())) {
                return loginTypeEnum;
            }
        }
        return null;
    }

}
