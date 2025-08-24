package com.fincodehub.finko.user.biz.constant;


/**
 * @title RedisKeyConstants
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 14:43
 * @description <TODO description class purpose>
 */
public class RedisKeyConstants {

    /**
     * Finko全局 ID 生成器 KEY
     */
    public static final String FINKO_ID_GENERATOR_KEY = "finko_id_generator";


    /**
     * 用户角色数据 KEY 前缀
     */
    private static final String USER_ROLES_KEY_PREFIX = "user:roles:";


    /**
     * 用户对应的角色集合 KEY
     * @param userId
     * @return
     */
    public static String buildUserRoleKey(Long userId) {
        return USER_ROLES_KEY_PREFIX + userId;
    }

    /**
     * 角色对应的权限集合 KEY 前缀
     */
    private static final String ROLE_PERMISSIONS_KEY_PREFIX = "role:permissions:";

    /**
     * 构建角色对应的权限集合 KEY
     * @param roleKey
     * @return
     */
    public static String buildRolePermissionsKey(String roleKey) {
        return ROLE_PERMISSIONS_KEY_PREFIX + roleKey;
    }


    private static final String PUSH_PERMISSION_FLAG_KEY = "push.permission.flag";
    public static String buildPushPermissionFlagKey() {
        return PUSH_PERMISSION_FLAG_KEY;
    }
}
