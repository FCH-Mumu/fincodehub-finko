package com.fincodehub.finko.user.relation.biz.rpc;

import cn.hutool.core.collection.CollUtil;
import com.fincodehub.finko.user.api.UserFeignApi;
import com.fincodehub.finko.user.dto.req.FindUserByIdReqDTO;
import com.fincodehub.finko.user.dto.req.FindUsersByIdsReqDTO;
import com.fincodehub.finko.user.dto.resp.FindUserByIdRspDTO;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 * @title UserRpcService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/13 21:05
 * @description: 用户服务
 **/
@Component
public class UserRpcService {

    @Resource
    private UserFeignApi userFeignApi;

    /**
     * 根据用户 ID 查询
     *
     * @param userId
     * @return
     */
    public FindUserByIdRspDTO findById(Long userId) {
        FindUserByIdReqDTO findUserByIdReqDTO = new FindUserByIdReqDTO();
        findUserByIdReqDTO.setId(userId);

        ResponseObject<FindUserByIdRspDTO> response = userFeignApi.findById(findUserByIdReqDTO);

        if (!response.isSuccess() || Objects.isNull(response.getData())) {
            return null;
        }

        return response.getData();
    }
    /**
     * 批量查询用户信息
     *
     * @param userIds
     * @return
     */
    public List<FindUserByIdRspDTO> findByIds(List<Long> userIds) {
        FindUsersByIdsReqDTO findUsersByIdsReqDTO = new FindUsersByIdsReqDTO();
        findUsersByIdsReqDTO.setIds(userIds);

        ResponseObject<List<FindUserByIdRspDTO>> response = userFeignApi.findByIds(findUsersByIdsReqDTO);

        if (!response.isSuccess() || Objects.isNull(response.getData()) || CollUtil.isEmpty(response.getData())) {
            return null;
        }

        return response.getData();
    }
}

