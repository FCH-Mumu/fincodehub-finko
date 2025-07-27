package com.fincodehub.finko.auth.alarm;

/**
 * @title AlarmInterface
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 12:02
 * @description <TODO description class purpose>
 */
public interface AlarmInterface {
    /**
     * 发送告警信息
     *
     * @param message
     * @return
     */
    boolean send(String message);
}
