dependencies {
    // bootstrap
    implementation(projectLibs.spring.bootstrap.starter)
    //implementation 'org.springframework.boot:spring-boot-starter-webflux'
    //    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // nacos
    implementation(projectLibs.nacos.discovery)
    // 网关
    implementation(projectLibs.spring.gateway)
    // 负载均衡
    implementation(projectLibs.spring.loadbalancer)

    // Sa-Token 权限认证，在线文档：https://sa-token.cc
    implementation(projectLibs.sa.token.starter)

    // Sa-Token 整合 Redis （使用 jackson 序列化方式）
    implementation(projectLibs.sa.token.redis.jackson)
    // 提供Redis连接池
    implementation(projectLibs.commons.pool2)

    // 引入finko-common
    implementation(project(":finko-framework:finko-commom"))

    // 引入redis相关
    implementation(projectLibs.spring.redis)
    implementation(projectLibs.sa.token.redis.jackson)

}

