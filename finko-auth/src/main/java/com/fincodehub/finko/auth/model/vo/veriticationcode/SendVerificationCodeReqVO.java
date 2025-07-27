package com.fincodehub.finko.auth.model.vo.veriticationcode;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title SendVerificationCodeReqVO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 14:51
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendVerificationCodeReqVO {

    @NotBlank(message = "手机号不能为空")
    private String phone;

}