package com.fincodehub.finko.user.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @title SexEnum
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/23 8:30
 * @description 性别枚举
 */
@Getter
@AllArgsConstructor
public enum SexEnum {
    WOMAN(0),
    MAN(1),
    SECRET(-1);

    private final Integer sex;

    public static boolean isValid(Integer sex){
        for (SexEnum loginTypeEnum : SexEnum.values()){
            if ( Objects.equals(sex, loginTypeEnum.getSex())){
                return true;
            }
        }
        return false;
    }

}
