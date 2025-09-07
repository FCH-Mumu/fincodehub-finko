package com.fincodehub.finko.note.biz.domain.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 笔记表
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-09-06
 */
@Data
@Builder
@TableName("sys_note")
@Schema(name = "Note", description = "笔记表")
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 内容是否为空(0：不为空 1：空)
     */
    @Schema(description = "内容是否为空(0：不为空 1：空)")
    private Boolean isContentEmpty;

    /**
     * 发布者ID
     */
    @Schema(description = "发布者ID")
    private Long creatorId;

    /**
     * 话题ID
     */
    @Schema(description = "话题ID")
    private Long topicId;

    /**
     * 话题名称
     */
    @Schema(description = "话题名称")
    private String topicName;

    /**
     * 是否置顶(0：未置顶 1：置顶)
     */
    @Schema(description = "是否置顶(0：未置顶 1：置顶)")
    private Boolean isTop;

    /**
     * 类型(0：图文 1：视频)
     */
    @Schema(description = "类型(0：图文 1：视频)")
    private Integer type;

    /**
     * 笔记图片链接(逗号隔开)
     */
    @Schema(description = "笔记图片链接(逗号隔开)")
    private String imgUris;

    /**
     * 视频链接
     */
    @Schema(description = "视频链接")
    private String videoUri;

    /**
     * 可见范围(0：公开,所有人可见 1：仅对自己可见)
     */
    @Schema(description = "可见范围(0：公开,所有人可见 1：仅对自己可见)")
    private Integer visible;

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
     * 状态(0：待审核 1：正常展示 2：被删除(逻辑删除) 3：被下架)
     */
    @Schema(description = "状态(0：待审核 1：正常展示 2：被删除(逻辑删除) 3：被下架)")
    private Integer status;

    /**
     * 笔记内容 UUID
     */
    @Schema(description = "笔记内容 UUID")
    private String contentUuid;

}
