package com.fincodehub.finko.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.finko.auth.domain.PermissionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-07-13
 */@Mapper
public interface PermissionDOMapper extends BaseMapper<PermissionDO> {

    /**
     * 3 表示按钮权限，因为普通用户目前来看，只有按钮权限需要控制
     * status = 0 启用的
     * id_deletedd = 0 逻辑删除,未删除
     */
    @Select("select id, name, permission_key from sys_permission where status = 0 and type = 3 and is_deleted = 0")
    List<PermissionDO> selectEnabledPermissionList();
}