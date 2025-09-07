package com.fincodehub.finko.note.biz.domain.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * 话题表
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-09-06
 */
@Data
@TableName("sys_topic")
@Schema(name = "Topic", description = "话题表")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 话题名称
     */
    @Schema(description = "话题名称")
    private String name;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime  updateTime;

    /**
     * 逻辑删除(0：未删除 1：已删除)
     */
    @Schema(description = "逻辑删除(0：未删除 1：已删除)")
    private Boolean isDeleted;
}
