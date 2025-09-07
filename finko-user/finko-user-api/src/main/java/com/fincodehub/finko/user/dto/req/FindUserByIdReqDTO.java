package com.fincodehub.finko.user.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title FindUserByIdReqDTO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/6 20:56
 * @description: 根据用户 ID 查询用户信息
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserByIdReqDTO {

    /**
     * 手机号
     */
    @NotNull(message = "用户 ID 不能为空")
    private Long id;

}