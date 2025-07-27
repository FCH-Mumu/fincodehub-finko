package com.fincodehub.finko.auth.constant;


/**
 * @title RedisKeyConstants
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 14:43
 * @description <TODO description class purpose>
 */
public class RedisKeyConstants {

    /**
     * 验证码 KEY 前缀
     */
    private static final String VERIFICATION_CODE_KEY_PREFIX = "verification_code:";

    /**
     * 构建验证码 KEY
     * @param phone
     * @return
     */
    public static String buildVerificationCodeKey(String phone) {
        return VERIFICATION_CODE_KEY_PREFIX + phone;
    }

    /**
     * Finko全局 ID 生成器 KEY
     */
    public static final String FINKO_ID_GENERATOR_KEY = "finko_id_generator";


    /**
     * 用户角色数据 KEY 前缀
     */
    private static final String USER_ROLES_KEY_PREFIX = "user:roles:";


    /**
     * 构建用户-角色 Key
     * @param phone
     * @return
     */
    public static String buildUserRoleKey(String phone) {
        return USER_ROLES_KEY_PREFIX + phone;
    }

    /**
     * 角色对应的权限集合 KEY 前缀
     */
    private static final String ROLE_PERMISSIONS_KEY_PREFIX = "role:permissions:";

    /**
     * 构建角色对应的权限集合 KEY
     * @param roleId
     * @return
     */
    public static String buildRolePermissionsKey(Long roleId) {
        return ROLE_PERMISSIONS_KEY_PREFIX + roleId;
    }


    private static final String PUSH_PERMISSION_FLAG_KEY = "push.permission.flag";
    public static String buildPushPermissionFlagKey() {
        return PUSH_PERMISSION_FLAG_KEY;
    }
}
