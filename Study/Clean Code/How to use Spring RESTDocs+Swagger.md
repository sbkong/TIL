- [[REST Docs 사용하기]] 에서 이어짐

# Swagger
### 장점
- REST Docs 에 없는 기능
    - 직접 테스트 가능
    - 클라이언트 가독성이 좋음

### 단점
- 소스 코드에 Swagger Annotation을 입력해야 해서 코드가 어지러워짐

### +RESTDocs
- RESTDocs로 생성한 문서를 Swagger로 변환하여 사용한다.

### 알아둘 점
- 테스트 통신을 해야 하므로 Web 연결 필요(Java 설치 필요)
# 개념
RESTDocs + swagger 문서를 제작하는 로직은 아래와 같다.

1. 기존처럼 테스트 코드를 통해 docs 문서를 생성
2. docs 문서를 OpenAPI3 스펙으로 변환
3. 만들어진 OpenAPI3 스펙을 SwaggerUI로 생성
4. 생성된 SwaggerUI를 static 패키지에 복사 및 정적 리소스로 배포

# 의존성 주입
### buid.gradle
```
// 1. Import 추가
import com.sun.security.ntlm.Server
import org.hidetake.gradle.swagger.generator.GenerateSwaggerUI
import org.springframework.boot.gradle.tasks.bundling.BootJar

// 2. buildscript 추가
buildscript {
    ext {
        restdocsApiSpecVersion = '0.17.1'
    }
}

plugins {
    id 'java'
    id 'org.springframework.boot' version '3.1.2'
    id 'io.spring.dependency-management' version '1.1.2'
    // 3. openAPI 플러그인 추가
    id 'com.epages.restdocs-api-spec' version "${restdocsApiSpecVersion}"
	// 4. swaggerUI 플러그인 추가
    id 'org.hidetake.swagger.generator' version '2.18.2'
}

group = 'study.project'
version = '0.0.1-SNAPSHOT'

java {
    sourceCompatibility = '17'
}

repositories {
    mavenCentral()
}

// 5. 생성된 API 스펙이 어느 위치에 있는지 지정
swaggerSources {
    sample {
        setInputFile(file("${project.buildDir}/api-spec/openapi3.yaml"))
    }
}

// 6. openapi3 스펙 생성시 설정 정보
openapi3 {
    servers = [
            { url = "http://배포중인 주소" },
            { url = "http://localhost:8080" }
    ]
    title = "API 문서"
    description = "RestDocsWithSwagger Docs"
    version = "0.0.1"
    format = "yaml"
}

dependencies {
	.
    .
    .
    
    // 7. RestDocs 추가
    testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'
    // 8. openAPI3 추가
    testImplementation 'com.epages:restdocs-api-spec-mockmvc:' + restdocsApiSpecVersion
	// 9. SwaggerUI 추가
    swaggerUI 'org.webjars:swagger-ui:4.11.1'
}

tasks.named('test') {
    useJUnitPlatform()
}

// 10. openapi3가 먼저 실행 - doFrist를 통한 Header 설정 (글에서 자세하게 설명)
tasks.withType(GenerateSwaggerUI) {
    dependsOn 'openapi3'
    doFirst {
        def swaggerUIFile = file("${openapi3.outputDirectory}/openapi3.yaml")

        def securitySchemesContent =  "  securitySchemes:\n" +  \
                                      "    APIKey:\n" +  \
                                      "      type: apiKey\n" +  \
                                      "      name: Authorization\n" +  \
                                      "      in: header\n" + \
                                      "security:\n" +
                                      "  - APIKey: []  # Apply the security scheme here"

        swaggerUIFile.append securitySchemesContent
    }
}

// 11. 생성된 openapi3 스펙을 기반으로 SwaggerUISample 생성 및 static/docs 패키지에 복사
bootJar {
    dependsOn generateSwaggerUISample
    from("${generateSwaggerUISample.outputDir}") {
        into 'static/docs'
    }
}
```

# 테스트 코드 작성

- [[REST Docs 사용하기]]에서 다루었던 restdocs 테스트 코드를 그대로 사용

### `DriverControllerDocsTest`
> - docs 테스트 코드 작성시 document (변경전)
```
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
 ```
> - docs + swagger 테스트 코드 작성시 document(변경후)
```
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
```

위와 같이 기존 docs에서 import 구문을 변경하는 것 만으로 docs+swagger를 적용할 수 있지만, swagger의 정보와 설정을 변경하기 위해서 아래와 같이 테스트 코드를 리팩터링 할 수 있다.

```
...

mockMvc.perform(  
                // when  
                RestDocumentationRequestBuilders.post("/api/driver/login")  
                    .param("driverId", "2")  
                    .param("password", "2222")  
                    .param("loginState", "1")  
                    .param("deviceDiv", "1")  
                    .param("regId", "please_input_gcm_code")  
            )  
  
            // then  
            .andDo(print())  
            .andExpect(status().isOk())  
            .andDo(document("driver-login",  
                preprocessRequest(prettyPrint()),  
                preprocessResponse(prettyPrint()),  
                resource(ResourceSnippetParameters.builder()  
                    .tag("Driver API")  
                    .summary("운전원 로그인 API")  
                    .formParameters(  
                        parameterWithName("driverId").description("기사 ID"),  
                        parameterWithName("password").description("비밀번호"),  
                        parameterWithName("loginState").description("로그인 상태 - 1:로그인, 2:로그아웃"),  
                        parameterWithName("deviceDiv").description("태블릿/휴대폰 구분 - 1:태블릿, 2:휴대폰"),  
                        parameterWithName("regId").description("GCM Key")  
                    )  
  
                    //TODO DriverContoller의 리턴 값을 아래와 같이 JSON 타입으로 변경 후 적용할 것  
//                .responseFields(  
//                    fieldWithPath("data").description("ID 존재 여부")  
//                    fieldWithPath("code").type(JsonFieldType.NUMBER)  
//                        .description("상태 코드"),  
//                    fieldWithPath("message").type(JsonFieldType.STRING)  
//                        .description("응답 메시지"),  
//                    fieldWithPath("data").type(JsonFieldType.BOOLEAN)  
//                        .description("ID 존재 여부")  
//                )  
  
                    .requestSchema(Schema.schema("FormParameter-driver-login"))  
                    .responseSchema(Schema.schema("UserResponse.Login"))  
                    .build())));
                    
					...
```

# 실행
- Gradle > Tasks > build > bootJar 실행
  ![[Pasted image 20240802151648.png]]

- CLI
  >./gradlew build

- web 실행
```bash
cd build/libs
java -jar {생성된 jar 파일}
```

# 결과 확인
- 아래 URL 접속
```
localhost:{port}/docs/index.html
```
![[Pasted image 20240802155352.png]]
# Reference
- [RESTDocs+Swagger tutorial](https://velog.io/@hwsa1004/Spring-restdocs-swagger-%EA%B0%99%EC%9D%B4-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0)