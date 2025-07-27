package com.finko.framework.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @title PhoneNumber
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/13 20:14
 * @description <TODO description class purpose>
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
    String message() default "手机号码格式错误, 需要11位数字";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}