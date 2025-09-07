package com.fincodehub.finko.note.biz.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.finko.note.biz.domain.dataobject.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 话题表 Mapper 接口
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-09-06
 */
@Mapper
public interface TopicMapper extends BaseMapper<Topic> {


    /**
     * 根据主键查询 name 字段
     * @param id 主键ID
     * @return name 字符串
     */
    @Select("SELECT NAME FROM SYS_TOPIC WHERE id = #{id}")
    String selectNameByPrimaryKey(@Param("id") Long id);
}
