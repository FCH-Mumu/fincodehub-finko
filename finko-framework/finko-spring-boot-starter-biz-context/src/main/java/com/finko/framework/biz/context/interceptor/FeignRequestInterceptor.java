package com.finko.framework.biz.context.interceptor;

import com.finko.framework.biz.context.holder.LoginUserContextHolder;
import com.finko.framework.common.constant.GlobalConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @title FeignRequestInterceptor
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/23 22:06
 * @description Feign 请求拦截器
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 获取当前上下文中的用户ID
        Long userId = LoginUserContextHolder.getUserId();

        // 若不为空，则添加到请求头中
        if ( Objects.nonNull(userId)){
            requestTemplate.header(GlobalConstants.USER_ID, String.valueOf(userId));
            log.info("==> feign 请求头中添加了 userId: {}",  userId);
        }
    }
}
