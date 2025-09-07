package com.fincodehub.finko.note.biz.rpc;

import com.fincodehub.finko.user.api.UserFeignApi;
import com.fincodehub.finko.user.dto.req.FindUserByIdReqDTO;
import com.fincodehub.finko.user.dto.resp.FindUserByIdRspDTO;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @title UserRpcService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/7 9:04
 * @description 用户服务
 */
@Component
public class UserRpcService {
    @Resource
    private UserFeignApi userFeignApi;
    /**
     * 根据 ID 查询用户信息
     *
     * @param id 用户 ID
     * @return 用户信息
     */
    public FindUserByIdRspDTO findById(Long id) {
        FindUserByIdReqDTO findUserByIdReqDTO = new FindUserByIdReqDTO();
        findUserByIdReqDTO.setId(id);
        ResponseObject<FindUserByIdRspDTO> responseObject = userFeignApi.findById(findUserByIdReqDTO);
        if (Objects.isNull(responseObject) || !responseObject.isSuccess()){
            return null;
        }
        return responseObject.getData();
    }
}
