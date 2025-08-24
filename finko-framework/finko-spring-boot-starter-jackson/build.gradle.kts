project.description="自定义 Jackson 配置: 支持 Java 8 新的日期 API，如 LocalDateTime、LocalDate 等等"

dependencies {
    // 引入finko-common
    implementation(project(":finko-framework:finko-commom"))

    implementation(projectLibs.spring.boot.autoconfigure)
}

