package com.fincodehub.finko.user.dto.req;

import com.finko.framework.common.validator.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title RegisterUserReqDTO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/23 23:15
 * @description 用户注册
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserReqDTO {

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @PhoneNumber
    private String phone;

}