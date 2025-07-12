package com.fincodehub.finko.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @title FinkoApplicationTest
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/9 21:43
 * @description <TODO description class purpose>
 */
@SpringBootTest
@Slf4j
public class FinkoApplicationTest {
    //
    // @Resource
    // private UserDOMapper userDOMapper;
    //
    // /**
    //  * 测试插入数据
    //  */
    // @Test
    // void testInsert() {
    //     UserDO userDO = UserDO.builder()
    //             .username("Finko")
    //             .createTime(LocalDateTime.now())
    //             .updateTime(LocalDateTime.now())
    //             .build();
    //
    //     userDOMapper.insert(userDO);
    // }
    //
    // /**
    //  * 查询数据
    //  */
    // @Test
    // void testSelect() {
    //     // 查询主键 ID 为 1 的记录
    //     UserDO userDO = userDOMapper.selectByPrimaryKey(1L);
    //     log.info("User: {}", JsonUtils.toJsonString(userDO));
    // }
    //
    // /**
    //  * 更新数据
    //  */
    // @Test
    // void testUpdate() {
    //     UserDO userDO = UserDO.builder()
    //             .id(1L)
    //             .username("FCH.Finko")
    //             .updateTime(LocalDateTime.now())
    //             .build();
    //
    //     // 根据主键 ID 更新记录
    //     userDOMapper.updateByPrimaryKey(userDO);
    // }
    //
    // /**
    //  * 删除数据
    //  */
    // @Test
    // void testDelete() {
    //     // 删除主键 ID 为 1 的记录
    //     userDOMapper.deleteByPrimaryKey(1L);
    // }
}
