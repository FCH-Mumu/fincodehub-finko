package com.fincodehub.finko.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * 用户权限表
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-07-27
 */
@Data
@TableName("sys_role_permission_rel")
@Schema(name = "RolePermissionDO", description = "用户权限表")
public class RolePermissionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private Long roleId;

    /**
     * 权限ID
     */
    @Schema(description = "权限ID")
    private Long permissionId;

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
