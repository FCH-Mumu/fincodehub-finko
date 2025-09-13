package com.fincodehub.finko.note.biz.constant;

/**
 * @title MQConstants
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/7 22:34
 * @description <TODO description class purpose>
 */
public interface MQConstants {

    /**
     * Topic 主题：删除笔记本地缓存
     */
    String TOPIC_DELETE_NOTE_LOCAL_CACHE = "DeleteNoteLocalCacheTopic";

    /**
     * Topic 主题：延迟双删 Redis 笔记缓存
     */
    String TOPIC_DELAY_DELETE_NOTE_REDIS_CACHE = "DelayDeleteNoteRedisCacheTopic";

}