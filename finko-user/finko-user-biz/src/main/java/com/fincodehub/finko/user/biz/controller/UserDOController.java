package com.fincodehub.finko.user.biz.controller;

import com.fincodehub.finko.user.biz.model.vo.UpdateUserInfoReqVO;
import com.fincodehub.finko.user.biz.service.IUserDOService;
import com.fincodehub.finko.user.dto.req.FindUserByPhoneReqDTO;
import com.fincodehub.finko.user.dto.req.RegisterUserReqDTO;
import com.fincodehub.finko.user.dto.req.UpdateUserPasswordReqDTO;
import com.fincodehub.finko.user.dto.resp.FindUserByPhoneRspDTO;
import com.finko.framework.biz.operationlog.aspect.ApiOperationLog;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-08-17
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserDOController {

    @Resource
    private IUserDOService userService;

    /**
     * 用户信息修改
     *
     * @param updateUserInfoReqVO
     * @return
     */
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseObject<?> updateUserInfo(@Validated UpdateUserInfoReqVO updateUserInfoReqVO) {
        log.info("==============用户信息修改：{}", updateUserInfoReqVO);
        return userService.updateUserInfo(updateUserInfoReqVO);
    }

    // ===================================== 对其他服务提供的接口 =====================================
    @PostMapping("/register")
    @ApiOperationLog(description = "用户注册")
    public ResponseObject<Long> register(@Validated @RequestBody RegisterUserReqDTO registerUserReqDTO) {
        return userService.register(registerUserReqDTO);
    }

    @PostMapping("/findByPhone")
    @ApiOperationLog(description = "手机号查询用户信息")
    public ResponseObject<FindUserByPhoneRspDTO> findByPhone(@Validated @RequestBody FindUserByPhoneReqDTO findUserByPhoneReqDTO) {
        return userService.findUserByPhone(findUserByPhoneReqDTO);
    }
    @PostMapping("/password/update")
    @ApiOperationLog(description = "密码更新")
    public ResponseObject<?> updatePassword(@Validated @RequestBody UpdateUserPasswordReqDTO updateUserPasswordReqDTO) {
        return userService.updatePassword(updateUserPasswordReqDTO);
    }
}
