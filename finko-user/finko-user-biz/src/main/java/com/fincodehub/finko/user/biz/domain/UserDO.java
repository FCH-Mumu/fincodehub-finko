package com.fincodehub.finko.user.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-08-17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
@Schema(name = "UserDO", description = "用户表")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * finko号(唯一凭证)
     */
    @Schema(description = "finko号(唯一凭证)")
    private String finkoId;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 生日
     */
    @Schema(description = "生日")
    private LocalDate birthday;

    /**
     * 背景图
     */
    @Schema(description = "背景图")
    private String backgroundImg;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;

    /**
     * 性别(0：女 1：男)
     */
    @Schema(description = "性别(0：女 1：男)")
    private Integer sex;

    /**
     * 状态(0：启用 1：禁用)
     */
    @Schema(description = "状态(0：启用 1：禁用)")
    private Integer status;

    /**
     * 个人简介
     */
    @Schema(description = "个人简介")
    private String introduction;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime  createTime;

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
