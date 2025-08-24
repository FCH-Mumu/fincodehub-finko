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
}
