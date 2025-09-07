package com.fincodehub.finko.kv.dto.rsp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @title FindNoteContentRspDTO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/30 21:49
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindNoteContentRspDTO {
    /**
     * 笔记 UUID
     */
    private UUID uuid;

    /**
     * 笔记内容
     */
    private String content;
}
