package com.fincodehub.finko.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.finko.auth.domain.RolePermissionDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户权限表 Mapper 接口
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-07-13
 */@Mapper
public interface RolePermissionDOMapper extends BaseMapper<RolePermissionDO> {
    List<RolePermissionDO> selectByRoleIds(@Param("roleIds") List<Long> roleIds);
}