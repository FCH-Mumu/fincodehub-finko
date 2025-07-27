package com.fincodehub.finko.auth.alarm;


import com.fincodehub.finko.auth.alarm.impl.MailAlarmHelper;
import com.fincodehub.finko.auth.alarm.impl.SmsAlarmHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title AlarmConfig
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 12:09
 * @description <TODO description class purpose>
 */
@Configuration
public class AlarmConfig {
    @Autowired
    private AlarmProperties alarmProperties;

    @Bean
    @RefreshScope
    public AlarmInterface alarmHelper() {
        String alarmType = alarmProperties.getAlarmType();
        if ("sms".equals(alarmType)) {
            return new SmsAlarmHelper();
        } else if ("mail".equals(alarmType)) {
            return new MailAlarmHelper();
        } else {
            throw new IllegalArgumentException("错误的告警类型...");
        }
    }
}