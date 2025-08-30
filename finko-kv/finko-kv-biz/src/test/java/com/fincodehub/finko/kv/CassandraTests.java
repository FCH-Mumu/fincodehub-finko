package com.fincodehub.finko.kv;

import com.fincodehub.finko.kv.domain.dataobject.NoteContentDO;
import com.fincodehub.finko.kv.domain.repository.NoteContentRepository;
import com.finko.framework.common.util.JsonUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

/**
 * @title CassandraTests
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/30 10:27
 * @description <TODO description class purpose>
 */
@SpringBootTest
@Slf4j
class CassandraTests {

    @Resource
    private NoteContentRepository noteContentRepository;

    /**
     * 测试插入数据
     */
    @Test
    void testInsert() {
        NoteContentDO nodeContent = NoteContentDO.builder()
                .id(UUID.randomUUID())
                .content("代码测试笔记内容插入1")
                .build();

        noteContentRepository.save(nodeContent);
    }

    /**
     * 测试修改数据
     */
    @Test
    void testUpdate() {
        NoteContentDO nodeContent = NoteContentDO.builder()
                .id(UUID.fromString("eaad1222-f091-40be-b824-0c9f275724a7"))
                .content("代码测试笔记内容更新1")
                .build();

        noteContentRepository.save(nodeContent);
    }

    /**
     * 测试查询数据
     */
    @Test
    void testSelect() {
        Optional<NoteContentDO> optional = noteContentRepository.findById(UUID.fromString("eaad1222-f091-40be-b824-0c9f275724a7"));
        optional.ifPresent(noteContentDO -> log.info("查询结果：{}", JsonUtils.toJsonString(noteContentDO)));
    }

    /**
     * 测试删除数据
     */
    @Test
    void testDelete() {
        noteContentRepository.deleteById(UUID.fromString("eaad1222-f091-40be-b824-0c9f275724a7"));
    }


}