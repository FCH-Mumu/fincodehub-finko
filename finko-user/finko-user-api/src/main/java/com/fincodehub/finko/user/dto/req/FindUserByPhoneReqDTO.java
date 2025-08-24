package com.fincodehub.finko.user.dto.req;

import com.finko.framework.common.validator.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title FindUserByPhoneReqDTO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/24 9:13
 * @description 根据手机号查询用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserByPhoneReqDTO {

    @NotBlank(message = "手机号不能为空")
    @PhoneNumber
    private String phone;
}
