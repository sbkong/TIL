# 파일 수정
### buid.gradle
```
plugins {  
    ... 
    id "com.gorylenko.gradle-git-properties" version "2.4.1"
    id "org.asciidoctor.jvm.convert" version "3.3.2"  
    id "com.epages.restdocs-api-spec" version "0.16.2"  
}

configurations {  
    ...
    asciidoctorExt  
}
  
ext {  
    set("snippetsDir", file("build/generated-snippets"))  
}

dependencies {  
    ...
    implementation("org.springdoc:springdoc-openapi-ui:1.6.11")  
    implementation("org.springdoc:springdoc-openapi-webmvc-core:1.6.11")
    
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")  
	//@see <a href="https://github.com/ePages-de/restdocs-api-spec">Spring REST Docs API specification Integration</a>  
	testImplementation("com.epages:restdocs-api-spec:0.16.2")  
	testImplementation("com.epages:restdocs-api-spec-mockmvc:0.16.2")  
	  
	asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
}

tasks.named('test') {  
    outputs.dir snippetsDir  
    ...
}

tasks.register("restDocsTest", Test) {  
    outputs.dir snippetsDir  
    useJUnitPlatform {  
        includeTags("restDocs")  
    }  
  
    finalizedBy "asciidoctor"  
    finalizedBy "openapi3"  
}

/** Java REST Docs */  
tasks.register("restDocsTest", Test) {  
    outputs.dir snippetsDir  
    useJUnitPlatform {  
        includeTags("restDocs")  
    }  
  
    finalizedBy "asciidoctor"  
    finalizedBy "openapi3"  
}  
  
/**  
 * <p> * 개발환경에서만 API문서를 제공하고자 한다면 빌드태스크에 asciidoctor 를 추가합니다.  
 * asciidoctor 가 수행될 때 통합테스트 실행 태스크 restDocsTest 를 수행하기에  
 * restDocsTest 를 추가로 수행하지 않아도 됩니다.  
 * </p> * @see <a href="https://asciidoctor.github.io/asciidoctor-gradle-plugin/development-3.x/user-guide/">Asciidoctor Gradle Plugin Suite</a> * * ex)<code>./gradlew clean asciidoctor build</code> */tasks.named("asciidoctor") {  
    dependsOn restDocsTest  
  
    inputs.dir snippetsDir  
    configurations "asciidoctorExt"  
    baseDirFollowsSourceDir() // 원본파일작업은 .adoc 디렉터리 기준  
}  
  
openapi3 {  
    servers = [  
            { url = 'http://localhost:8080' },  
    ]  
    title = 'spring-rest-docs-guide'  
    description = 'Spring REST Docs 테스트 생성물 생성시 추가생성되는 OpenAPI 문서이용'  
    version = "${project.version}"  
    format = 'yaml'  
}  
  
tasks.register("apiBuild", GradleBuild) {  
    tasks = ["clean", "restDocsTest", "build"]  
}  
  
springBoot {  
    buildInfo()  
}  
  
gitProperties {  
    dateFormat = "yyyy-MM-dd'T'HH:mm:ss.Zz"  
    dateFormatTimeZone = "Asia/Seoul"  
    failOnNoGitDirectory = false  
}  
  
bootJar {  
    from("swagger-ui") {  
        into "BOOT-INF/classes/static/swagger"  
    }  
    from("${asciidoctor.outputDir}") {  
        into "BOOT-INF/classes/static/docs"  
    }  
    from("build/api-spec") {  
        into "BOOT-INF/classes/static/swagger"  
    }  
  
    archiveFileName.set "application.jar"  
}
```

### asciidoc
- API 문서 생성시 기준이 되는 최상위 아스키닥(asciidoc) 문서
    - 경로 : `src/docs/asciidoc/index.adoc`

### Custom Snippets
- Spring REST Docs에서 제공하는 스티펫 형식을 커스터마이징
    - 경로 : `src/test/org.springframework.restdocs.templates.asciidoctor`

> [!Caution]
> "Spring REST Docs" 에서 제공하는 스니펫을 구미에 맞게 변경(사용자정의, custom)하기 위해 사용자정의 스니펫 파일을 작성하는데, 그 위치가 약간 헷갈릴 수 있습니다. 문서에서는 `src/test/resources/org/springframework/restdocs/templates/asciidoctor` 경로에 변경하길 원하는 스니펫 파일을 정의하라고 기술했습니다.


# 실행 방법
- Gradle > tasks > other > restDocsTest 실행
  ![[Pasted image 20240801133400.png]]