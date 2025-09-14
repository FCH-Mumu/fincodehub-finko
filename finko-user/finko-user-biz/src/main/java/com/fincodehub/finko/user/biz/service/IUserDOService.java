package com.fincodehub.finko.user.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fincodehub.finko.user.biz.domain.UserDO;
import com.fincodehub.finko.user.biz.model.vo.UpdateUserInfoReqVO;
import com.fincodehub.finko.user.dto.req.FindUserByIdReqDTO;
import com.fincodehub.finko.user.dto.req.FindUserByPhoneReqDTO;
import com.fincodehub.finko.user.dto.req.FindUsersByIdsReqDTO;
import com.fincodehub.finko.user.dto.req.RegisterUserReqDTO;
import com.fincodehub.finko.user.dto.req.UpdateUserPasswordReqDTO;
import com.fincodehub.finko.user.dto.resp.FindUserByIdRspDTO;
import com.fincodehub.finko.user.dto.resp.FindUserByPhoneRspDTO;
import com.finko.framework.common.response.ResponseObject;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-08-17
 */
public interface IUserDOService extends IService<UserDO> {

    /**
     * 更新用户信息
     *
     * @param updateUserInfoReqVO
     * @return
     */
    ResponseObject<?> updateUserInfo(UpdateUserInfoReqVO updateUserInfoReqVO);

    /**
     * 用户注册
     *
     * @param registerUserReqDTO
     * @return
     */
    ResponseObject<Long> register(RegisterUserReqDTO registerUserReqDTO);

    /**
     * 根据手机号查询用户信息
     *
     * @param findUserByPhoneReqDTO
     * @return
     */
    ResponseObject<FindUserByPhoneRspDTO> findUserByPhone(FindUserByPhoneReqDTO findUserByPhoneReqDTO);

    /**
     * 修改密码
     *
     * @param updateUserPasswordReqDTO
     * @return
     */
    ResponseObject<?> updatePassword(UpdateUserPasswordReqDTO updateUserPasswordReqDTO);
    /**
     * 根据用户 ID 查询用户信息
     *
     * @param findUserByIdReqDTO
     * @return
     */
    ResponseObject<FindUserByIdRspDTO> findById(FindUserByIdReqDTO findUserByIdReqDTO);
    /**
     * 批量根据用户 ID 查询用户信息
     *
     * @param findUsersByIdsReqDTO
     * @return
     */
    ResponseObject<List<FindUserByIdRspDTO>> findByIds(FindUsersByIdsReqDTO findUsersByIdsReqDTO);
}
