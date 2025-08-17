package com.fincodehub.finko.auth.service;

import com.fincodehub.finko.auth.domain.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fincodehub.finko.auth.model.vo.user.UpdatePasswordReqVO;
import com.fincodehub.finko.auth.model.vo.user.UserLoginReqVO;
import com.finko.framework.common.response.ResponseObject;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-07-27
 */
public interface IUserDOService extends IService<UserDO> {
    /**
     * 登录与注册
     * @param userLoginReqVO
     * @return
     */
    ResponseObject<String> loginAndRegister(UserLoginReqVO userLoginReqVO);

    /**
     * 登出
     * @return
     */
    ResponseObject<?> logout();
    /**
     * 修改密码
     * @param updatePasswordReqVO
     * @return
     */
    ResponseObject<?> updatePassword(UpdatePasswordReqVO updatePasswordReqVO);
}
