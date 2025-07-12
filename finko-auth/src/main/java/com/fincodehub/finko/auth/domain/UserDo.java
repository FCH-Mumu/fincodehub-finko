package com.fincodehub.finko.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-07-12
 */
@Data
@TableName("sys_user")
@Schema(name = "UserDo", description = "用户表")
public class UserDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    @NotBlank(message = "用户账号不能为空")
    @Schema(description = "用户账号")
    private String uid;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 用户工号
     */
    @Schema(description = "用户工号")
    private String userno;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;

    /**
     * 密码哈希值（使用 bcrypt / argon2 加密）
     */
    @Schema(description = "密码哈希值（使用 bcrypt / argon2 加密）")
    private String passwordHash;

    /**
     * 盐值（用于增强密码加密强度，非必须）
     */
    @Schema(description = "盐值（用于增强密码加密强度，非必须）")
    private String salt;

    /**
     * 账户状态：0-禁用，1-启用，2-待激活，3-锁定
     */
    @Schema(description = "账户状态：0-禁用，1-启用，2-待激活，3-锁定")
    private Boolean status;

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
     * 最后一次更新时间
     */
    @Schema(description = "最后一次更新时间")
    private LocalDateTime lastLoginTime;
}
