dependencies {
    implementation(projectLibs.spring.web)
    testImplementation(projectLibs.spring.boot.test)
    testRuntimeOnly(projectLibs.junit.platform.launcher)
    // 引入finko-common
    implementation(project(":finko-framework:finko-commom"))

    implementation(projectLibs.nacos.discovery)
    // bootstrap
    implementation(projectLibs.spring.bootstrap.starter)
    implementation(projectLibs.nacos.cloud.config)
    implementation(projectLibs.minio)

    // oss
    implementation(projectLibs.aliyun.oss.sdk)
    implementation(projectLibs.jaxb.api)
    implementation(projectLibs.activation)
    implementation(projectLibs.jaxb.runtime)

    // 业务日志接口
    implementation(project(":finko-framework:finko-spring-boot-starter-biz-operationlog"))

    // JackSon组件
    implementation(project(":finko-framework:finko-spring-boot-starter-jackson"))
    // 上下文依赖
    implementation(project(":finko-framework:finko-spring-boot-starter-biz-context"))
}
