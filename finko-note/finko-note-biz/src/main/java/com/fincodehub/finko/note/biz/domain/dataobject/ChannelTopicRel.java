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
 * 频道-话题关联表
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-09-06
 */
@Data
@TableName("sys_channel_topic_rel")
@Schema(name = "ChannelTopicRel", description = "频道-话题关联表")
public class ChannelTopicRel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 频道ID
     */
    @Schema(description = "频道ID")
    private Long channelId;

    /**
     * 话题ID
     */
    @Schema(description = "话题ID")
    private Long topicId;

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
}
