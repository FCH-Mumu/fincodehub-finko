package com.fincodehub.finko.user.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.finko.user.biz.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-08-17
 */
@Mapper
public interface UserDOMapper extends BaseMapper<UserDO> {

    void updateByPrimaryKeySelective(UserDO userDO);
    /**
     * 根据手机号查询记录
     * @param phone
     * @return
     */
    @Select("select id, password from sys_user where phone = #{phone}  ")
    UserDO selectByPhone(String phone);

    @Select("select * from sys_user where id=#{userId}")
    UserDO selectByPrimaryKey(Long userId);

    /**
     * 批量查询用户信息
     *
     * @param ids
     * @return
     */

    List<UserDO> selectByIds(@Param("ids") List<Long> ids);

}
