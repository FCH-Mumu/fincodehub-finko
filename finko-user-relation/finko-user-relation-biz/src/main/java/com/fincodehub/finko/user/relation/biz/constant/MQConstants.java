package com.fincodehub.finko.user.relation.biz.constant;

/**
 * @title MQConstants
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/14 8:34
 * @description: MQ 常量
 **/
public interface MQConstants {

    /**
     * Topic: 关注、取关共用一个
     */
    String TOPIC_FOLLOW_OR_UNFOLLOW = "FollowUnfollowTopic";

    /**
     * 关注标签
     */
    String TAG_FOLLOW = "Follow";

    /**
     * 取关标签
     */
    String TAG_UNFOLLOW = "Unfollow";
}