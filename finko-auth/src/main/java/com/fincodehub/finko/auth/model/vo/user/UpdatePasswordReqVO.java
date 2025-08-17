package com.fincodehub.finko.auth.model.vo.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title UpdatePasswordReqVO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/17 8:06
 * @description 修改用户密码
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePasswordReqVO {

    @NotBlank(message = "新密码不能为空")
    private String newPassword;

}