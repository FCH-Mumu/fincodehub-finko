package com.fincodehub.finko.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.finko.auth.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-07-27
 */
@Mapper
public interface UserDOMapper extends BaseMapper<UserDO> {
    /**
     * 根据手机号查询记录
     * @param phone
     * @return
     */
    @Select("select id, password from sys_user where phone = #{phone}  ")
    UserDO selectByPhone(String phone);
}
