package com.fincodehub.finko.auth.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.finko.auth.domain.dataobject.UserDO;

/**
 * @title UserDOMapper
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/9 21:23
 * @description <TODO description class purpose>
 */
public interface UserDOMapper extends BaseMapper<UserDO> {
    /**
     * 根据主键 ID 查询
     * @param id
     * @return
     */
    UserDO selectByPrimaryKey(Long id);

    /**
     * 根据主键 ID 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(UserDO record);

    /**
     * 更新记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserDO record);
}
