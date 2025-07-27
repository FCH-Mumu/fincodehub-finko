package com.fincodehub.finko.auth.model.vo.user;

import com.finko.framework.common.validator.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title UserLoginReqVO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 14:29
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginReqVO
{

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @PhoneNumber
    private
    String phone;

    /**
     * 验证码
     */
    private
    String code;

    /**
     * 密码
     */
    private
    String password;

    /**
     * 登录类型：手机号验证码，或者是账号密码
     */
    @NotNull(message = "登录类型不能为空")
    private
    Integer type;
}
