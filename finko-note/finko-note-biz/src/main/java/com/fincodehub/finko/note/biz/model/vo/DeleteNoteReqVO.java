package com.fincodehub.finko.note.biz.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title DeleteNoteReqVO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/8 0:46
 * @description: 笔记发布
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteNoteReqVO {

    @NotNull(message = "笔记 ID 不能为空")
    private Long id;

}