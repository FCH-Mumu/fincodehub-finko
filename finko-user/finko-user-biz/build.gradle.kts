dependencies {
    // 引入finko-common
    implementation(project(":finko-framework:finko-commom"))
    implementation(projectLibs.spring.web)
    testImplementation(projectLibs.spring.boot.test)
    testRuntimeOnly(projectLibs.junit.platform.launcher)

    implementation(projectLibs.nacos.discovery)
    // bootstrap
    implementation(projectLibs.spring.bootstrap.starter)

    // 引入mybatis依赖
    implementation(projectLibs.mybatis.plus.boot.starter)
    implementation(projectLibs.mybatis.plus.generator)
    implementation(projectLibs.mysql.connector.java)
    // 引入durid
    implementation(projectLibs.druid.spring.boot.starter)

    implementation(projectLibs.jakarta.validation.api)
    implementation(projectLibs.hibernate.validator)

    implementation(projectLibs.springdoc)
    implementation(projectLibs.swagger.annotations)
    implementation(projectLibs.freemarker)


    // 业务日志接口
    implementation(project(":finko-framework:finko-spring-boot-starter-biz-operationlog"))
    // 上下文组件
    implementation(project(":finko-framework:finko-spring-boot-starter-biz-context"))

    // JackSon组件
    implementation(project(":finko-framework:finko-spring-boot-starter-jackson"))
    // finko-oss
    implementation(project(":finko-oss:finko-oss-api"))

    // finko-user
    implementation(project(":finko-user:finko-user-api"))

    // 引入redis相关
    implementation(projectLibs.spring.redis)
    implementation(projectLibs.commons.pool2)

    implementation(project(":finko-distributed-id-generator:finko-distributed-id-generator-api"))

}