dependencies {
    // 引入finko-common
    implementation(project(":finko-framework:finko-commom"))
    // 引入operationlog
    implementation(project(":finko-framework:finko-spring-boot-starter-biz-operationlog"))
    // 上下文依赖
    implementation(project(":finko-framework:finko-spring-boot-starter-biz-context"))
    // jackson
    implementation(project(":finko-framework:finko-spring-boot-starter-jackson"))

    implementation(projectLibs.spring.web)
    testImplementation(projectLibs.spring.boot.test)
    testRuntimeOnly(projectLibs.junit.platform.launcher)

    // nacos
    implementation(projectLibs.nacos.discovery)
    implementation(projectLibs.nacos.cloud.config)

    // bootstrap
    implementation(projectLibs.spring.bootstrap.starter)

    // 引入mybatis依赖
    implementation(projectLibs.mybatis.plus.boot.starter)
    implementation(projectLibs.mybatis.plus.generator)
    implementation(projectLibs.mysql.connector.java)
    // 引入durid
    implementation(projectLibs.druid.spring.boot.starter)


    // 代码生成器相关
    implementation(projectLibs.jakarta.validation.api)
    implementation(projectLibs.hibernate.validator)

    implementation(projectLibs.springdoc)
    implementation(projectLibs.swagger.annotations)
    implementation(projectLibs.freemarker)

    // 引入redis相关
    implementation(projectLibs.spring.redis)
    implementation(projectLibs.commons.pool2)

    implementation(project(":finko-user:finko-user-api"))
    implementation(projectLibs.rocketmq)
}
