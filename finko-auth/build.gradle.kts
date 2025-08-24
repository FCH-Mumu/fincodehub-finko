dependencies {
    implementation(projectLibs.spring.web)
    testImplementation(projectLibs.spring.boot.test)
    testRuntimeOnly(projectLibs.junit.platform.launcher)
    // 引入finko-common
    implementation(project(":finko-framework:finko-commom"))
    // 引入operationlog
    implementation(project(":finko-framework:finko-spring-boot-starter-biz-operationlog"))
    // 上下文依赖
    implementation(project(":finko-framework:finko-spring-boot-starter-biz-context"))
    // jackson
    implementation(project(":finko-framework:finko-spring-boot-starter-jackson"))


    implementation(projectLibs.springdoc)
    implementation(projectLibs.swagger.annotations)
    implementation(projectLibs.freemarker)

    //nacos
    // implementation(projectLibs.nacos.config)
    implementation(projectLibs.nacos.cloud.config)
    implementation(projectLibs.nacos.discovery)


    // 引入sa-token
    implementation(projectLibs.sa.token)

    // 引入redis相关
    implementation(projectLibs.spring.redis)
    implementation(projectLibs.commons.pool2)
    implementation(projectLibs.sa.token.redis.jackson)

    implementation(projectLibs.aliyun.dysmsapi)
    // bootstrap
    implementation(projectLibs.spring.bootstrap.starter)

    // TransmittableThreadLocal
    implementation(projectLibs.transmittable.thread.local)
    // 加密
    implementation(projectLibs.spring.security.crypto)

    // 引入user-api模块
    implementation(project(":finko-user:finko-user-api"))

}