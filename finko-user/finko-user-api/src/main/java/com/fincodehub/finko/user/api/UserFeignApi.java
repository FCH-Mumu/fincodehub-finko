package com.fincodehub.finko.user.api;

import com.fincodehub.finko.user.constant.ApiConstants;
import com.fincodehub.finko.user.dto.req.FindUserByPhoneReqDTO;
import com.fincodehub.finko.user.dto.req.RegisterUserReqDTO;
import com.fincodehub.finko.user.dto.req.UpdateUserPasswordReqDTO;
import com.fincodehub.finko.user.dto.resp.FindUserByPhoneRspDTO;
import com.finko.framework.common.response.ResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @title UserFeignApi
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/23 23:32
 * @description <TODO description class purpose>
 */
@FeignClient(name = ApiConstants.SERVICE_NAME)
public interface UserFeignApi {

    String PREFIX = "/user";

    @PostMapping(value = PREFIX + "/register")
    ResponseObject<Long> registerUser(@RequestBody RegisterUserReqDTO registerUserReqDTO);

    @PostMapping(value = PREFIX + "/findByPhone")
    ResponseObject<FindUserByPhoneRspDTO> findUserByPhone(@RequestBody FindUserByPhoneReqDTO findUserByPhoneReqDTO);

    @PostMapping(value = PREFIX + "/password/update")
    ResponseObject<?> updatePassword(@RequestBody UpdateUserPasswordReqDTO updateUserPasswordReqDTO);
}
