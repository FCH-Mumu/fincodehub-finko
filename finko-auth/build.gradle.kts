dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // 引入finko-common
    implementation(project(":finko-framework:finko-commom"))
    // 引入operationlog
    implementation(project(":finko-framework:finko-spring-boot-starter-biz-operationlog"))
    // 引入mybatis依赖
    implementation(projectLibs.mybatis.plus.boot.starter)
    implementation(projectLibs.mybatis.plus.generator)
    implementation(projectLibs.mysql.connector.java)
    // 引入durid
    implementation(projectLibs.druid.spring.boot.starter)

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
}