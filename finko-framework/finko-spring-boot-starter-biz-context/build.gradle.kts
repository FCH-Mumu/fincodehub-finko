dependencies {
    // 引入finko-common
    implementation(project(":finko-framework:finko-commom"))

    // TransmittableThreadLocal
    implementation(projectLibs.transmittable.thread.local)

    implementation(projectLibs.spring.web)
    implementation(projectLibs.spring.boot.starter)
    implementation(projectLibs.jakarta.servlet.api)

    implementation(projectLibs.feign.core)

}
