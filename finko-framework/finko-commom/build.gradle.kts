
dependencies {

    api(projectLibs.jackson.databind)
    api(projectLibs.jackson.core)
    //  解决 Jackson Java 8 新日期 API 的序列化问题
    api(projectLibs.jackson.datatype.jsr310)

    api(projectLibs.jakarta.validation.api)
    api(projectLibs.hibernate.validator)
}