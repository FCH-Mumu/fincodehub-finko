package com.fincodehub.finko.user.dto.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @title FindUsersByIdsReqDTO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/14 19:58
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUsersByIdsReqDTO {

    @NotNull(message = "用户 ID 集合不能为空")
    @Size(min = 1, max = 10, message = "用户 ID 集合大小必须大于等于 1, 小于等于 10")
    private List<Long> ids;

}