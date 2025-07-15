package com.fincodehub.finko.auth.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;
import jakarta.validation.constraints.NotNull;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @title MybatisPlusGenerator
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/10 16:36
 * @description <TODO description class purpose>
 */
@Component("mybatisPlusGenerator")
public class MybatisPlusGenerator {
  @Value("${spring.datasource.url}")
  private String mysqlUrl;

  @Value("${spring.datasource.username}")
  private String mysqlUsername;

  // @Value("${spring.datasource.password}")
  private String mysqlPassword = "yj123456YJ..";

  /** 实际使用版 Spring Boot项目生成全部表示例，有调整可以自己修改 */
  public String generatorToLocal(String tableName) {
    //  业务名
    String businessName = "auth";
    // 模板保存路径
    String templatePath = "generator"; // resource/generator
    String plusTemplatePath = templatePath + "/plus/";
    String packageName = "com.fincodehub.finko.auth";
    if (StringUtils.isBlank(packageName)) {
      packageName = "";
    } else {
      packageName = packageName + "." + businessName;
    }
    String outputDir = System.getProperty("user.dir") + "/finko-auth/src/main/java";

    FastAutoGenerator.create(mysqlUrl, mysqlUsername, mysqlPassword)
        .globalConfig(
            builder -> {
              builder
                  .disableOpenDir() // 允许自动打开输出目录
                  .outputDir(outputDir) // 设置输出目录
                  .author("FCH丨木木") // 设置作者
                  .enableSpringdoc() // 启用Springdoc
                  .dateType(DateType.ONLY_DATE) // 设置时间类型策略
                  .commentDate("yyyy-MM-dd"); // 设置时间类型
            })
        .packageConfig(
            builder -> {
              builder
                  .parent("com.fincodehub.finko.auth") // 设置父包名
                  // .moduleName("finko-auth") // 设置父包模块名
                  .entity("domain") // 设置实体类所在的子包名
                  .controller("controller") // 设置Controller所在的子包名
                  .service("service") // 设置Service所在的子包名
                  .serviceImpl("service.impl") // 设置ServiceImpl类所在的子包名
                  .mapper("mapper") // 设置Mapper接口所在的子包名
                   .xml("mapper") // 设置Mapper XML文件所在的子包名
                  .pathInfo(
                      Collections.singletonMap(
                          OutputFile.xml,
                          System.getProperty("user.dir")
                              + "/finko-auth/src/main/resources/mapper")); // 设置路径配置信息
            })
        .strategyConfig(
            builder -> {
              builder
                  //.addInclude(tableName)
                  .enableSkipView() // 允许跳过视图
                  .controllerBuilder()
                  .enableFileOverride() // 允许覆盖已生成文件
                  .entityBuilder()
                  .enableFileOverride() // 允许覆盖已生成文件
                  .enableLombok(new ClassAnnotationAttributes("@Data", "lombok.Data")) // 使用Lombok
                  .nameConvert(
                      new INameConvert() {
                        // 名称转换实现
                        @Override
                        public String entityNameConvert(@NotNull TableInfo tableInfo) {
                          // 自定义表名转Entity
                          // 针对t_user表返回UserDo
                          if ("sys_user".equalsIgnoreCase(tableInfo.getName())) {
                            return "UserDo";
                          }
                          // 其他表使用默认命名规则(下划线转驼峰)
                          return NamingStrategy.capitalFirst(
                              NamingStrategy.underlineToCamel(tableInfo.getName()));
                        }

                        @Override
                        public String propertyNameConvert(@NotNull TableField field) {
                          // 字段名使用默认命名规则(下划线转驼峰)
                          return NamingStrategy.underlineToCamel(field.getName());
                        }
                      })
                  // .javaTemplate(plusTemplatePath + "entity.java.vm") // 设置实体类模板路径
                  // .superClass(com.fincodehub.config.BasePojo.class) // 设置继承父类;
                  .serviceBuilder()
                  .enableFileOverride() // 允许覆盖已生成文件
                  // .disableService() // 禁用默认的Service接口
                  // .serviceTemplate(plusTemplatePath + "service.java.vm") // 设置Service接口模板路径
                  // .serviceImplTemplate(plusTemplatePath + "serviceImpl.java.vm") //
                  // 设置ServiceImpl类模板路径
                  .mapperBuilder()
                  .enableFileOverride() // 允许覆盖已生成文件
                  // .mapperTemplate(plusTemplatePath + "mapper.java.vm") // 设置Mapper接口模板路径
                  // .disableMapper() // 禁用默认的Mapper接口
                  .mapperAnnotation(Mapper.class); // 使用Mapper注解
            })
        .injectionConfig(
            builder -> {
              builder.customMap(
                  new HashMap<String, Object>() {
                    {
                      put("moduleName", "comments"); // 模块名（英文）
                      put("businessName", businessName); // 业务名（英文）
                      put("functionName", "评论"); // 功能名（中文）
                      put("javaType", "String"); // 表主键java类型
                    }
                  });
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
