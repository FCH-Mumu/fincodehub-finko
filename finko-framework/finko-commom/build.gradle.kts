
dependencies {

    implementation(projectLibs.jackson.databind)
    implementation(projectLibs.jackson.core)
    //  解决 Jackson Java 8 新日期 API 的序列化问题
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    implementation(projectLibs.jakarta.validation.api)
    implementation(projectLibs.hibernate.validator)
}