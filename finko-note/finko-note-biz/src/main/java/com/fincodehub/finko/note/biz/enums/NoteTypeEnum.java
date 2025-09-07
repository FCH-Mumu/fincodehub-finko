package com.fincodehub.finko.note.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @title NoteTypeEnum
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/6 12:33
 * @description 笔记类型
 */
@Getter
@AllArgsConstructor
public enum NoteTypeEnum {
    IMAGE_TEXT(0, "图文"),
    VIDEO(1, "视频");
    private final Integer code;
    private final String description;
    /**
     * 校验参数是否有效
     *
     * @param code 编码
     * @return
     */
    public static boolean isValid(Integer code) {
        for (NoteTypeEnum noteTypeEnum : NoteTypeEnum.values()) {
            if (Objects.equals(code, noteTypeEnum.getCode())) {
                return true;
            }
        }
        return false;
    }
    /**
     * 根据code获取枚举
     *
     * @param code 编码
     * @return
     */
    public static NoteTypeEnum valueOf(Integer code){
        for (NoteTypeEnum noteTypeEnum : NoteTypeEnum.values()){
            if (Objects.equals(code, noteTypeEnum.getCode())){
                return noteTypeEnum;
            }
        }
        return null;
    }
}
