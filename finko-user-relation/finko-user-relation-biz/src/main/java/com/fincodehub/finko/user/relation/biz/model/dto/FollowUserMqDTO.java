package com.fincodehub.finko.user.relation.biz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @title FollowUserMqDTO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/14 8:33
 * @description: 关注用户
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowUserMqDTO {

    private Long userId;

    private Long followUserId;

    private LocalDateTime createTime;
}