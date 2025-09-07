package com.fincodehub.finko.note.biz.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title FindNoteDetailReqVO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/7 8:49
 * @description 笔记详情
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindNoteDetailReqVO {

    @NotNull(message = "笔记 ID 不能为空")
    private Long id;

}