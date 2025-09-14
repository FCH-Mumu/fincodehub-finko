package com.fincodehub.finko.user.relation.biz.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title FindFollowingUserRspVO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/14 20:47
 * @description: 查询关注列表
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindFollowingUserRspVO {

    private Long userId;

    private String avatar;

    private String nickname;

    private String introduction;

}