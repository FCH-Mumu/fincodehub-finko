package com.finko.framework.biz.operationlog.aspect;

import java.lang.annotation.*;

/**
 * @title ApiOperationLog
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/8 21:15
 * @description <TODO description class purpose>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    /**
     * API 功能描述
     *
     * @return
     */
    String description() default "";

}