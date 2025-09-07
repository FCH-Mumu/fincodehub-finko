package com.fincodehub.finko.note.biz.service.impl;

import com.fincodehub.finko.note.biz.domain.dataobject.Topic;
import com.fincodehub.finko.note.biz.domain.mapper.TopicMapper;
import com.fincodehub.finko.note.biz.service.ITopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 话题表 服务实现类
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-09-06
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {

}
