package com.finko.framework.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @title PhoneNumberValidator
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/13 20:05  
 * @description 手机号自定义验证器 PhoneNumber：自定义注解类型。String：被校验的属性类型。  
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    /**
     * 初始化方法 这个方法在校验器实例化后会被调用，通常用来读取注解中的参数来设置校验器的初始状态  
     * @param constraintAnnotation
     */
    @Override
    public void initialize(PhoneNumber constraintAnnotation){
        // 初始化操作  
    }

    /**
     * 校验逻辑  
     * @param phoneNumber 需要验证的字符串，即被注解的属性值。  
     * @param context 提供了一些校验的上下文信息，通常用来设置错误消息等  
     * @return
     */
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context){
        // 校验逻辑：正则表达式判断手机号是否为 11 位数字  
        // \\d{11} 验证字符串是否为 11 位的数字。\\d 表示匹配一个数字字符，{11} 表示匹配前面的模式正好 11 次  
        return phoneNumber != null && phoneNumber.matches("\\d{11}");
    }

}