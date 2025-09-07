package com.fincodehub.finko.note.biz.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @title MybatisPlusGenerator
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/17 22:13
 * @description <TODO description class purpose>
 */
@Component("mybatisUserPlusGenerator")
public class MybatisUserPlusGenerator {
  @Value("${spring.datasource.url}")
  private String mysqlUrl;

  @Value("${spring.datasource.username}")
  private String mysqlUsername;

  // @Value("${spring.datasource.password}")
  private String mysqlPassword = "yj123456YJ..";

  /** 实际使用版 Spring Boot项目生成全部表示例，有调整可以自己修改 */
  public String generatorToLocal(String tableName) {
    //  业务名
    String businessName = "note";
    // 模板保存路径
    String templatePath = "generator"; // resource/generator
    String plusTemplatePath = templatePath + "/plus/";
    String packageName = "com.fincodehub.finko.note.biz";
    if (StringUtils.isBlank(packageName)) {
      packageName = "";
    } else {
      packageName = packageName + "." + businessName;
    }
    String outputDir = System.getProperty("user.dir") + "/finko-note/finko-note-biz/src/main/java";

    // 1. 配置数据源
    FastAutoGenerator.create(mysqlUrl, mysqlUsername, mysqlPassword)
        // 2. 全局配置
        .globalConfig(
            builder -> {
              builder
                  .author("FCH丨木木") // 设置作者
                  .commentDate("yyyy-MM-dd") // 注释日期格式
                  .outputDir(outputDir) // 生成文件输出目录
                  .disableOpenDir() // 生成后不自动打开文件夹
                  .enableSpringdoc() // 启用Springdoc
                  .dateType(DateType.ONLY_DATE); // 设置时间类型策略
            })
        // 3. 包配置
        .packageConfig(
            builder -> {
              builder
                  .parent("com.fincodehub.finko.note.biz") // 设置父包名
                  .entity("domain.dataobject") // 设置实体类所在的子包名
                  .controller("controller") // 设置Controller所在的子包名
                  .service("service") // 设置Service所在的子包名
                  .serviceImpl("service.impl") // 设置ServiceImpl类所在的子包名
                  .mapper("domain.mapper") // 设置Mapper接口所在的子包名
                  .xml("mapper") // Mapper映射文件包名（默认在resources下）
                  .pathInfo(
                      Collections.singletonMap(
                          OutputFile.xml,
                          System.getProperty("user.dir")
                              + "/finko-note/finko-note-biz/src/main/resources/mapper"));
            })
        // 4.   // 策略配置
        .strategyConfig(
            builder -> {
              builder
                  // .addInclude(tableName) // 设置需要生成的表名（可多个）
                  .addTablePrefix("sys_") // 忽略表名前缀（如t_book -> Book）
                  .enableSkipView() // 允许跳过视图
                  // 实体类策略配置
                  .entityBuilder()
                  .enableFileOverride() // 允许覆盖已生成文件
                  .enableLombok(
                      new ClassAnnotationAttributes("@Data", "lombok.Data")) // 开启Lombok注解（@Data等）
                  .naming(NamingStrategy.underline_to_camel) // 表名下划线转驼峰
                  .columnNaming(NamingStrategy.underline_to_camel) // 列名下划线转驼峰
                  .logicDeleteColumnName("deleted") // 逻辑删除字段（如有）
                  // Controller策略配置
                  .controllerBuilder()
                  .enableFileOverride() // 允许覆盖已生成文件
                  .enableRestStyle() // 开启REST风格（@RestController）
                  .serviceBuilder()
                  .enableFileOverride() // 允许覆盖已生成文件
                  .mapperBuilder()
                  .enableFileOverride() // 允许覆盖已生成文件
                  .mapperAnnotation(Mapper.class); // 使用Mapper注解
            })
        .templateEngine(new FreemarkerTemplateEngine())
        .execute();

    return null;
  }

  public static void main(String[] args) {

    // generatorToLocal(); // 实际使用版
  }

  public List<String> getAllTables() {
    List<String> tables = new ArrayList<>();
    // 获取数据库所有表
    try (Connection conn = DriverManager.getConnection(mysqlUrl, mysqlUsername, "yj123456YJ..")) {
      System.out.println("连接成功！");

      // 方法一：使用 SHOW TABLES
      try (Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery("SHOW TABLES")) {

        System.out.println("数据库 finko 中的表：");
        while (rs.next()) {
          String tableName = rs.getString(1);
          tables.add(tableName);
          System.out.println(tableName);
        }
      }

      // 方法二：使用 information_schema （更结构化）
      //            String query = "SELECT TABLE_NAME FROM information_schema.TABLES " +
      //                           "WHERE TABLE_SCHEMA = 'finko'";
      //            try (Statement stmt = conn.createStatement();
      //                 ResultSet rs = stmt.executeQuery(query)) {
      //
      //                while (rs.next()) {
      //                    String tableName = rs.getString("TABLE_NAME");
      //                    System.out.println(tableName);
      //                }
      //            }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tables;
  }
}
