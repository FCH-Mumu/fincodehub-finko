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
 * 权限表
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-07-27
 */
@Data
@TableName("sys_permission")
@Schema(name = "PermissionDO", description = "权限表")
public class PermissionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父ID
     */
    @Schema(description = "父ID")
    private Long parentId;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    private String name;

    /**
     * 类型(1：目录 2：菜单 3：按钮)
     */
    @Schema(description = "类型(1：目录 2：菜单 3：按钮)")
    private Integer type;

    /**
     * 菜单路由
     */
    @Schema(description = "菜单路由")
    private String menuUrl;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String menuIcon;

    /**
     * 管理系统中的显示顺序
     */
    @Schema(description = "管理系统中的显示顺序")
    private Integer sort;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String permissionKey;

    /**
     * 状态(0：启用；1：禁用)
     */
    @Schema(description = "状态(0：启用；1：禁用)")
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除(0：未删除 1：已删除)
     */
    @Schema(description = "逻辑删除(0：未删除 1：已删除)")
    private Boolean isDeleted;
}
