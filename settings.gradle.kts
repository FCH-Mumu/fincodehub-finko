import java.net.URI

rootProject.name = "fincodehub-finko"

dependencyResolutionManagement {
    versionCatalogs {
        create("projectLibs") {
            from(files("./libs.versions.toml"))
        }
    }
    // 稳定方式配置仓库（保持原样，目前 Gradle 8+ 仅支持此写法）
    repositories {
        // 阿里云仓库
        maven { url = URI("https://maven.aliyun.com/repository/public/") }
        maven { url = URI("https://maven.aliyun.com/repository/jcenter") }
        // 公司私库
        maven {
            url = uri("https://www.fincodehub.com/repository/private") // 自定义私有仓库
            credentials {
                username = "fincodehub" // 如果需要认证
                password = "123456"
            }
        }
        mavenCentral()
    }
}

include("finko-auth")
include("finko-framework")
include("finko-framework:finko-commom")
include("finko-framework:finko-spring-boot-starter-biz-operationlog")
include("finko-gateway")