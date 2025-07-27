package com.fincodehub.finko.auth.alarm;

import com.alibaba.cloud.nacos.annotation.NacosConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @title AlarmProperties
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/27 13:51
 * @description <TODO description class purpose>
 */
@Component
@RefreshScope
public class AlarmProperties {
    @NacosConfig(dataId = "finko-auth-dev", group = "DEFAULT_GROUP", key = "alarm.type")
    private String alarmType;

    public String getAlarmType() {
        return alarmType;
    }
}
