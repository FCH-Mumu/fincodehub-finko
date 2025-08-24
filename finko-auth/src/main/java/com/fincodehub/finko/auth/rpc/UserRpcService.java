package com.fincodehub.finko.auth.rpc;

import com.fincodehub.finko.user.api.UserFeignApi;
import com.fincodehub.finko.user.dto.req.FindUserByPhoneReqDTO;
import com.fincodehub.finko.user.dto.req.RegisterUserReqDTO;
import com.fincodehub.finko.user.dto.req.UpdateUserPasswordReqDTO;
import com.fincodehub.finko.user.dto.resp.FindUserByPhoneRspDTO;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @title UserRpcService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/23 23:48
 * @description <TODO description class purpose>
 */
@Component
public class UserRpcService {
    @Resource
    private UserFeignApi userFeignApi;
    /**
     * 用户注册
     */
    public Long registerUser(String phone) {
        RegisterUserReqDTO registerUserReqDTO = new RegisterUserReqDTO();
        registerUserReqDTO.setPhone(phone);
        ResponseObject<Long> responseObject =  userFeignApi.registerUser(registerUserReqDTO);
        if (!responseObject.isSuccess()){
            return null;
        }
        return responseObject.getData();
    }

    /**
     * 根据手机号查询用户信息
     */
    public FindUserByPhoneRspDTO findUserByPhone(String phone) {
        FindUserByPhoneReqDTO findUserByPhoneReqDTO = new FindUserByPhoneReqDTO();
        findUserByPhoneReqDTO.setPhone(phone);

        ResponseObject<FindUserByPhoneRspDTO> responseObject = userFeignApi.findUserByPhone(findUserByPhoneReqDTO);
        if (!responseObject.isSuccess()){
            return null;
        }
        return responseObject.getData() ;
    }
    /**
     * 密码更新
     */
    public void updatePassword(String encodePassword){
        UpdateUserPasswordReqDTO updateUserPasswordReqDTO = new UpdateUserPasswordReqDTO();
        updateUserPasswordReqDTO.setEncodePassword(encodePassword);
        userFeignApi.updatePassword(updateUserPasswordReqDTO);
    }
}
