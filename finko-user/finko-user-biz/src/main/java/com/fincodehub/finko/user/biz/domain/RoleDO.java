package com.fincodehub.finko.user.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-07-27
 */
@Data
@TableName("sys_role")
@Schema(name = "RoleDO", description = "角色表")
public class RoleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名
     */
    @Schema(description = "角色名")
    private String roleName;

    /**
     * 角色唯一标识
     */
    @Schema(description = "角色唯一标识")
    private String roleKey;

    /**
     * 状态(0：启用 1：禁用)
     */
    @Schema(description = "状态(0：启用 1：禁用)")
    private Integer status;

    /**
     * 管理系统中的显示顺序
     */
    @Schema(description = "管理系统中的显示顺序")
    private Integer sort;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 最后一次更新时间
     */
    @Schema(description = "最后一次更新时间")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除(0：未删除 1：已删除)
     */
    @Schema(description = "逻辑删除(0：未删除 1：已删除)")
    private Boolean isDeleted;
}
