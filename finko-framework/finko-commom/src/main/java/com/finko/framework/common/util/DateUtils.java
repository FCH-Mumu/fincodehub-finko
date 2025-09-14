package com.finko.framework.common.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @title DateUtils
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/13 22:58
 * @description: 日期工具类
 **/
public class DateUtils {

    /**
     * LocalDateTime 转时间戳
     *
     * @param localDateTime
     * @return
     */
    public static long localDateTime2Timestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}