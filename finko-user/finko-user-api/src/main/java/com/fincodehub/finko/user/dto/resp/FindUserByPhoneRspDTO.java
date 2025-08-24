package com.fincodehub.finko.user.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title FindUserByPhoneRspDTO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/24 9:16
 * @description 根据手机号查询用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserByPhoneRspDTO {
    private Long id;
    private String password;
}
