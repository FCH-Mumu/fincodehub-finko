package com.fincodehub.finko.note.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title NoteStatusEnum
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/6 13:18
 * @description 笔记状态
 */
@Getter
@AllArgsConstructor
public enum NoteStatusEnum {
    // 待审核
    BE_EXAMINE(0),
    // 正常展示
    NORMAL(1),
    // 逻辑删除
    DELETED(2),
    // 下架
    DOWNED(3),
    ;

    private final Integer code;
}
