dependencies {
    // 引入finko-common
    implementation(project(":finko-framework:finko-commom"))

    // feign
    api(projectLibs.spring.openfeign)
    // 负载均衡
    implementation(projectLibs.spring.loadbalancer)

    // feign form
    implementation(projectLibs.feign.form)
    implementation(projectLibs.feign.form.spring)
}

