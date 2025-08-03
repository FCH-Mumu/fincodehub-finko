package com.fincodehub.finko.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.finko.auth.domain.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-07-13
 */@Mapper
public interface RoleDOMapper extends BaseMapper<RoleDO> {

    @Select("SELECT id, role_key, role_name from sys_role where status = 0 and is_deleted = 0")
    List<RoleDO> selectEnabledRoleList();

    RoleDO selectByPrimaryKey(Long commonUserRoleId);
}