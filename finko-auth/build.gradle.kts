dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // 引入finko-common
    implementation(project(":finko-framework:finko-commom"))
    // 引入operationlog
    implementation(project(":finko-framework:finko-spring-boot-starter-biz-operationlog"))
    // 引入mybatis依赖
    implementation(projectLibs.mybatis.plus.boot.starter)
    implementation(projectLibs.mysql.connector.java)
}