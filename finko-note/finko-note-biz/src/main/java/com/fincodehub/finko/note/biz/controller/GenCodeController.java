package com.fincodehub.finko.note.biz.controller;

import com.fincodehub.finko.note.biz.config.MybatisUserPlusGenerator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title TestController
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/8 20:35
 * @description <TODO description class purpose>
 */
@RestController
@RequestMapping("/note")
public class GenCodeController {

  @Autowired private MybatisUserPlusGenerator mybatisPlusGenerator;

  /**
   * 生成Mybatis-plus代码
   *
   * @return
   */
  @GetMapping("/genCode")
  public String genCode() {
    System.out.println("tableName==================" );
    // 获取数据所有表
    List<String> tables = mybatisPlusGenerator.getAllTables();
    for (String table : tables) {
      System.out.println("table==================" + table);
      String path = mybatisPlusGenerator.generatorToLocal(table);
    }
    return "";
  }

}
