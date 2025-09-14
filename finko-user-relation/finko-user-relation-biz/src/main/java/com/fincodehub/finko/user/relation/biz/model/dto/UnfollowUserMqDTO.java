package com.fincodehub.finko.user.relation.biz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @title UnfollowUserMqDTO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/14 18:35
 * @description: 取关用户
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnfollowUserMqDTO {

    private Long userId;

    private Long unfollowUserId;

    private LocalDateTime createTime;
}