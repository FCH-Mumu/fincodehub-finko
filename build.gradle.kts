import java.io.FileInputStream
import java.util.Properties

// 定义库引用
val pLibs = projectLibs

val projectGroup: String = rootProject.extra["project_group"] as String
val projectVersion: String = rootProject.extra["project_version"] as String
val projectJdk: String = rootProject.extra["project_jdk"] as String



// 加载根路径自定义配置属性

// 加载环境属性文件
val envProps = Properties().apply {
    val envFile = file("env.properties")
    if (envFile.exists()) {
        FileInputStream(envFile).use { fis ->
            load(fis)
        }
    } else {
        logger.warn("env.properties 文件未找到，跳过加载环境配置")
    }
}

plugins {
    java
//    alias(projectLibs.plugins.springboot)
    alias(projectLibs.plugins.springdependency)
}

//allprojects：所有模块生效
allprojects {
    // 配置项目信息
    group = projectGroup
    version = projectVersion
    // 配置字符编码
    tasks.withType(JavaCompile::class).configureEach {
        options.encoding = "UTF-8"
    }


    // 仓库配置
    repositories {
        //本地
        mavenLocal()
        //阿里仓库
        maven(url = uri("https://maven.aliyun.com/repository/public/"))
        //公司仓库。必须用：https。Gradle默认情况下不允许使用不安全的协议，以提高安全性。
        maven(url = uri("https://www.fincodehub.com/repository/maven-public/"))
        //中央仓库
        mavenCentral()
    }

}
//subprojects：所有子模块生效——springboot配置、导包版本管理、打包管理
subprojects {
    // 所有子模块都是springboot项目
    apply {
        plugin("java")
//        plugin(pLibs.plugins.springboot.get().pluginId)
        plugin(pLibs.plugins.springdependency.get().pluginId)

    }
    // jdk版本
    configure<JavaPluginExtension> {
        val jdkVersion = JavaVersion.toVersion(projectJdk)
        sourceCompatibility = jdkVersion
        targetCompatibility = jdkVersion
        toolchain {
            languageVersion = JavaLanguageVersion.of(projectJdk)
        }
    }

    dependencyManagement {
        imports {
            mavenBom(pLibs.spring.boot.dependencies.get().toString())
            mavenBom(pLibs.spring.cloud.dependencies.get().toString())
            mavenBom(pLibs.spring.cloud.alibaba.dependencies.get().toString())

        }
    }

    dependencies {
//        implementation(platform(pLibs.spring.boot.dependencies))
//        implementation(platform(pLibs.spring.cloud.dependencies))
//        implementation(platform(pLibs.spring.cloud.alibaba.dependencies))

        // 示例：公共工具类可以引入 lombok、utils等
        compileOnly(pLibs.lombok)
        annotationProcessor(pLibs.lombok)
        testImplementation(pLibs.lombok)
        testAnnotationProcessor(pLibs.lombok)

        implementation(pLibs.commons.io)
        implementation(pLibs.fastjson)
        implementation(pLibs.commons.lang3)
        implementation(pLibs.guava)
        implementation(pLibs.hutool.all)
        implementation(pLibs.commons.pool2)
        implementation(pLibs.jasypt)


    }
    // 如果项目中存在多个版本的同一库，可以通过排除旧版本或冲突的版本。
    configurations.all {
//        exclude("org.springframework.boot","spring-boot-starter-logging")
//        exclude("ch.qos.logback") // 通常需要排除具体的模块
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

