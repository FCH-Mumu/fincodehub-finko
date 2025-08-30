dependencies {
    // 引入finko-common
    implementation(project(":finko-framework:finko-commom"))
    implementation(projectLibs.spring.web)
    testImplementation(projectLibs.spring.boot.test)
    testRuntimeOnly(projectLibs.junit.platform.launcher)

    implementation(projectLibs.nacos.discovery)
    // bootstrap
    implementation(projectLibs.spring.bootstrap.starter)

    //cassandra
    implementation(projectLibs.spring.cassandra)
//
//    implementation("com.datastax.oss:java-driver-core:4.17.0")
//    implementation("com.datastax.oss:java-driver-query-builder:4.17.0")
//    implementation("com.datastax.oss:java-driver-mapper-runtime:4.17.0")

    implementation(project(":finko-kv:finko-kv-api"))
}