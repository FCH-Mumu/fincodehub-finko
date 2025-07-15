package com.finko.framework.common.util;


import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.jasypt.iv.RandomIvGenerator;

/**
 * @title JasyptUtils
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/15 8:07
 * @description <TODO description class purpose>
 */
public class JasyptUtils {

    /**
     * 加密
     * @param password 加密密钥
     * @param plainText 明文密码
     * @return
     */
    public static String encrypt(String password,String plainText) {
        // 加密密钥，实际使用时可通过环境变量或其他安全方式获取
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        encryptor.setAlgorithm("PBEWithHMACSHA512ANDAES_256");
        encryptor.setPassword(password);

        encryptor.setIvGenerator(new RandomIvGenerator());

        String encryptedText = encryptor.encrypt(plainText);
        String decryptedText = encryptor.decrypt(encryptedText);

        System.out.println("原始文本: " + plainText);
        System.out.println("加密后: ENC(" + encryptedText + ")");
        System.out.println("解密后: " + decryptedText);
        return encryptedText;
    }
}
