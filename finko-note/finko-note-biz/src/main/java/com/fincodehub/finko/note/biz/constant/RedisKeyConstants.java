package com.fincodehub.finko.note.biz.constant;

/**
 * @title RedisKeyConstants
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/7 11:56
 * @description <TODO description class purpose>
 */
public class RedisKeyConstants {
    /**
     * 笔记详情 KEY 前缀
     */
    public static final String NOTE_DETAIL_KEY = "note:detail:";


    /**
     * 构建完整的笔记详情 KEY
     * @param noteId
     * @return
     */
    public static String buildNoteDetailKey(Long noteId) {
        return NOTE_DETAIL_KEY + noteId;
    }
}
