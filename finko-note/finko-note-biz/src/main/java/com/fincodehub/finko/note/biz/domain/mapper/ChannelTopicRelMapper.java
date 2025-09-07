package com.fincodehub.finko.note.biz.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.finko.note.biz.domain.dataobject.ChannelTopicRel;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 频道-话题关联表 Mapper 接口
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-09-06
 */
@Mapper
public interface ChannelTopicRelMapper extends BaseMapper<ChannelTopicRel> {

}
