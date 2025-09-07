package com.fincodehub.finko.note.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title NoteVisibleEnum
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/6 13:23
 * @description 笔记可见性
 */
@Getter
@AllArgsConstructor
public enum NoteVisibleEnum {
    // 公开,所有人可见
    PUBLIC(0),
    // 仅对自己可见
    PRIVATE(1),
    ;
    private final Integer code;
}
