package com.fincodehub.finko.kv.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title FindNoteContentReqDTO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/30 21:47
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindNoteContentReqDTO {
    @NotNull(message = "笔记 UUID 不能为空")
    private String uuid;
}
