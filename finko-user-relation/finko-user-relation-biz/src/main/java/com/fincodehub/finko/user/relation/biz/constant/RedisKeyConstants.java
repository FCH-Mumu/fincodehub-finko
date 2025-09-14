package com.fincodehub.finko.user.relation.biz.constant;

/**
 * @title RedisKeyConstants
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/13 22:56
 * @description <TODO description class purpose>
 */
public class RedisKeyConstants {
    /**
     * 关注列表 KEY 前缀
     */
    private static final String USER_FOLLOWING_KEY_PREFIX = "following:";

    /**
     * 构建关注列表完整的 KEY
     * @param userId
     * @return
     */
    public static String buildUserFollowingKey(Long userId) {
        return USER_FOLLOWING_KEY_PREFIX + userId;
    }
    /**
     * 粉丝列表 KEY 前缀
     */
    private static final String USER_FANS_KEY_PREFIX = "fans:";

    /**
     * 构建粉丝列表完整的 KEY
     * @param userId
     * @return
     */
    public static String buildUserFansKey(Long userId) {
        return USER_FANS_KEY_PREFIX + userId;
    }
}
