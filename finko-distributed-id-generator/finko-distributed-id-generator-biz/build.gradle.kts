dependencies {
    // 引入finko-common
    implementation(project(":finko-framework:finko-commom"))
    implementation(projectLibs.spring.web)
    testImplementation(projectLibs.spring.boot.test)
    testRuntimeOnly(projectLibs.junit.platform.launcher)

    implementation(projectLibs.nacos.discovery)
    // bootstrap
    implementation(projectLibs.spring.bootstrap.starter)


    implementation(projectLibs.perf4j)
    implementation(projectLibs.druid)
    // 引入mybatis依赖
    implementation(projectLibs.mybatis.plus.boot.starter)
//    implementation(projectLibs.mybatis.plus.generator)
    implementation(projectLibs.mysql.connector.java)

    // 为防止日志冲突，添加以下排除项
    // ZooKeeper Client
    implementation(projectLibs.curator.recipes) {
        exclude(group = "org.slf4j", module = "slf4j-api")
        exclude(group = "org.slf4j", module = "slf4j-log4j12")
        exclude(group = "log4j", module = "log4j")
    }
    // 为防止日志冲突，添加以下排除项
    // Curator Recipes (High-level ZooKeeper API)
    implementation(projectLibs.zookeeper) {
        exclude(group = "log4j", module = "log4j")
        exclude(group = "org.slf4j", module = "slf4j-reload4j")
        exclude(group = "org.slf4j", module = "slf4j-api")
        exclude(group = "org.apache.zookeeper", module = "zookeeper")
    }
}