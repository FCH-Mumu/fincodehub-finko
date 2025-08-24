package com.fincodehub.finko.user.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title UpdateUserPasswordReqDTO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/24 11:22
 * @description  密码更新
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserPasswordReqDTO {
    @NotBlank(message = "手机号不能为空")
    private String encodePassword;
}
