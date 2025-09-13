package com.fincodehub.finko.note.biz.consumer;

import com.fincodehub.finko.note.biz.constant.MQConstants;
import com.fincodehub.finko.note.biz.service.INoteService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @title DeleteNoteLocalCacheConsumer
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/7 22:44
 * @description 删除本地缓存
 */
@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "finko_group",// 消费组
        topic = MQConstants.TOPIC_DELETE_NOTE_LOCAL_CACHE, // 主题
        messageModel = MessageModel.BROADCASTING // 广播模式
)
public class DeleteNoteLocalCacheConsumer implements RocketMQListener<String> {

    @Resource
    private INoteService noteService;

    @Override
    public void onMessage(String body) {
        Long noteId = Long.valueOf(body);
        log.info("==> 消费者消费成功 noteId: {}", noteId);

        noteService.deleteNoteLocalCache(noteId);
    }
}
