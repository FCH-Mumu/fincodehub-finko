package com.fincodehub.finko.auth.alarm.impl;

import com.fincodehub.finko.auth.alarm.AlarmInterface;
import lombok.extern.slf4j.Slf4j;

/**
 * @title SmsAlarmHelper
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 12:06
 * @description <TODO description class purpose>
 */
@Slf4j
public class SmsAlarmHelper implements AlarmInterface {

    /**
     * 发送告警信息
     *
     * @param message
     * @return
     */
    @Override
    public boolean send(String message) {
        log.info("==> 【短信告警】：{}", message);

        // 业务逻辑...

        return true;
    }
}