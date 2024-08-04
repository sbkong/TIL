# 의존성 주입
### buid.gradle
```
plugins { 
	id "org.asciidoctor.jvm.convert" version "3.3.2"
}

configurations {
	asciidoctorExt 
}

dependencies {
	asciidoctorExt 'org.springframework.restdocs:spring-restdocs-asciidoctor:{project-version}' 
	testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc:{project-version}' 
}

ext { 
	snippetsDir = file('build/generated-snippets')
}

test { 
	outputs.dir snippetsDir
}

asciidoctor { 
	inputs.dir snippetsDir 
	configurations 'asciidoctorExt' 
	dependsOn test 
}


bootJar {
	dependsOn asciidoctor 
	from ("${asciidoctor.outputDir}/html5") { 
		into 'static/docs'
	}
}

```
- Apply the Asciidoctor plugin.
- Declare the asciidoctorExt configuration for dependencies that extend Asciidoctor.
- Add a dependency on spring-restdocs-asciidoctor in the asciidoctorExt configuration. This will automatically configure the snippets attribute for use in your .adoc files to point to build/generated-snippets. It will also allow you to use the operation block macro.
- Add a dependency on spring-restdocs-mockmvc in the testImplementation configuration. If you want to use WebTestClient or REST Assured rather than MockMvc, add a dependency on spring-restdocs-webtestclient or spring-restdocs-restassured respectively instead.
- Configure a snippetsDir property that defines the output location for generated snippets.
- Make Gradle aware that running the test task will write output to the snippetsDir. This is required for incremental builds.
- Configure the asciidoctor task.
- Make Gradle aware that running the task will read input from the snippetsDir. This is required for incremental builds.
- Configure the use of the asciidoctorExt configuration for extensions.
- Make the task depend on the test task so that the tests are run before the documentation is created.

- Ensure that the documentation has been generated before the jar is built.
- Copy the generated documentation into the jar’s static/docs directory.

# Custom Snippets 작성
- Spring REST Docs에서 제공하는 스니펫 형식을 커스터마이징
    - 경로 : `src/test/org.springframework.restdocs.templates.asciidoctor`
      ![[Pasted image 20240802114139.png]]

> [!Caution]
> "Spring REST Docs" 에서 제공하는 스니펫을 구미에 맞게 변경(사용자정의, custom)하기 위해 사용자정의 스니펫 파일을 작성하는데, 그 위치가 약간 헷갈릴 수 있습니다. 문서에서는 `src/test/resources/org/springframework/restdocs/templates/asciidoctor` 경로에 변경하길 원하는 스니펫 파일을 정의하라고 기술했습니다.

### `request-fields.snippet`
```
==== Request Fields  
|===  
|Path|Type|Optional|Description  
  
{{#fields}}  
  
|{{#tableCellContent}}`+{{path}}+`{{/tableCellContent}}  
|{{#tableCellContent}}`+{{type}}+`{{/tableCellContent}}  
|{{#tableCellContent}}{{#optional}}O{{/optional}}{{/tableCellContent}}  
|{{#tableCellContent}}{{description}}{{/tableCellContent}}  
  
{{/fields}}  
  
|===
```

### `response-fields.snippet`
```
.HTTP 응답예제  
[source,http,options="nowrap"]  
----  
HTTP/1.1{{statusCode}}{{statusReason}}  
{{#headers}}  
{{name}}:{{value}}  
{{/headers}}  
{{responseBody}}  
----
```


# API 문서 양식 만들기
### asciidoc
- API 문서 생성시 기준이 되는 최상위 아스키닥(asciidoc) 문서
    - 메인 문서 : `src/docs/asciidoc/index.adoc`
    - include 문서 : `src/docs/asciidoc/api/driver.adoc`
      ![[Pasted image 20240802115102.png]]
#### index.adoc
```
ifndef::snippets[]  
:snippets: ../../build/generated-snippets  
endif::[]  
  
= 복지콜 API 가이드  
공수빈, <sbkong@triniti.kr>  
v2024.08.01, 2024.08.01  
:doctype: book  
:icons: font  
:source-highlighter: coderay  
:toc: left  
:toc-title: 목차  
:toclevels: 3  
:sectlinks:  
:sectnums:  
  
== 개요  
  
이 API문서는 '2024 복지콜 재구축' 프로젝트의 산출물입니다.  
  
=== API 서버 경로  
  
[cols="2,5,3"]  
|====  
|환경         |DNS |비고  
|개발(dev)    | link:[] |API 문서 제공  
|베타(beta)   | link:[] |API 문서 제공  
|운영(prod)   | link:[] |API 문서 미제공  
|====  
  
[NOTE]  
====  
해당 프로젝트 API문서는 개발환경까지 노출되는 것을 권장합니다. +====  
  
[CAUTION]  
====  
운영환경에 노출될 경우 보안 관련 문제가 발생할 수 있습니다.  
====  
  
=== 응답형식  
  
프로젝트는 다음과 같은 응답형식을 제공합니다.  
  
==== 정상(200, OK)  
  
|====  
|응답데이터가 없는 경우|응답데이터가 있는 경우  
  
a|  
[source,json]  
----  
{  
    "code": "0000",    "message": "OK",    "data": null}  
----  
  
a|  
[source,json]  
----  
{  
    "code": "0000",    "message": "OK",    "data": {        "name": "honeymon-enterprise"    }}  
----  
|====  
  
==== 상태코드(HttpStatus)  
  
응답시 다음과 같은 응답상태 헤더, 응답코드 및 응답메시지를 제공합니다.  
  
[cols="3,1,3,3"]  
|====  
|HttpStatus |코드 |메시지 |설명  
  
|`OK(200)` |`0000` |"OK" |정상 응답  
|`INTERNAL_SERVER_ERROR(500)`|`S5XX` |"알 수 없는 에러가 발생했습니다. 관리자에게 문의하세요." |서버 내부 오류  
|`FORBIDDEN(403)`|`C403` |"[AccessDenied] 잘못된 접근입니다." |비인가 접속입니다.  
|`BAD_REQUEST(400)`|`C400` |"잘못된 요청입니다. 요청내용을 확인하세요." |요청값 누락 혹은 잘못된 기입  
|`NOT_FOUND(404)`|`C404` |"상황에 따라 다름" |요청값 누락 혹은 잘못된 기입  
  
|====  
  
== API  
  
//운전원  
include::api/driver.adoc[]
```

#### driver.adoc
- gradle > documentation > asciidoctor를 실행하면 `build/generated-snippets` 디렉터리 아래에 adoc 파일이 생성될 것이다. 이 중 필요한 것만 `driver.adoc` 파일에 추가(`include`)한다. 양식은 아래와 같다.
```
=== 운전원 API  
==== HTTP Request Body  
include::{snippets}/driver-login/http-request.adoc[]  
include::{snippets}/driver-login/request-body.adoc[]  
  
=== HTTP Response Body  
include::{snippets}/driver-login/http-response.adoc[]  
include::{snippets}/driver-login/response-body.adoc[]
```

# 테스트 코드 작성
- 프로젝트 구조
  ![[Pasted image 20240802115802.png]]
### `DriverController.class`
```
@RestController  
@RequestMapping("/api/driver")  
@AllArgsConstructor  
public class DriverController {  
  
    private final DriverService driverService;

    @PostMapping("/login")
    public String driverLogin(@RequestParam int driverId, @RequestParam String password, @RequestParam int loginState, @RequestParam int deviceDiv, @RequestParam String regId) {  
        return driverService.driverLogin(driverId, password, loginState, deviceDiv, regId, "");  
    }
}
```

### `DriverService.class`
```
@Service
@AllArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;


    private String makeHeader(boolean success, String reason) {
        if (success) {
            return "<RESULT>OK</RESULT>";
        } else {
            return "<RESULT>ERROR</RESULT><REASON>" + reason + "</REASON>";
        }
    }

    private String makeHeader(boolean success) {
        return makeHeader(success, "");
    }

    public String driverLogin(int driverId, String password, int loginState, int deviceDiv,
        String regId, String carNo) {
        Optional<DriverEntity> driverOpt = driverRepository.findById(driverId);
        if (driverOpt.isEmpty() || !driverOpt.get().getNmPasswd().equals(password)) {
            return makeHeader(false, "아이디 또는 비밀번호가 잘못되었습니다");
        }

        DriverStatusEntity driverStatus = driverStatusRepository.findByPkDrvid(driverId);
        if (driverStatus == null) {
            return makeHeader(false, "로그인 중 오류가 발생 하였습니다.");
        }

        if (loginState == 1) {
            String gcmRegIdField =
                (deviceDiv == 1) ? driverStatus.getGcmRegId() : driverStatus.getGcmRegId2();
            if ((deviceDiv == 1 && gcmRegIdField != null && !gcmRegIdField.isEmpty()) ||
                (deviceDiv == 2 && gcmRegIdField != null && !gcmRegIdField.isEmpty())) {
                return makeHeader(false, "이미 로그인 되어 있습니다. 관제센터로 연락하시기 바랍니다.");
            }
            if (regId == null || regId.isEmpty()) {
                return makeHeader(false, "GCM REG ID 값이 입력되지 않았습니다");
            }

            driverStatus.setGcmRegId((deviceDiv == 1) ? regId : "");
            driverStatus.setGcmRegId2((deviceDiv == 2) ? regId : "");
            driverStatus.setNmCarno((deviceDiv == 1) ? carNo : "");
            driverStatusRepository.save(driverStatus);

            MobileSettingEntity mobileSetting = mobileSettingRepository.findById(driverId)
                .orElse(null);
            if (mobileSetting == null) {
                return makeHeader(false, "로그인 중 오류가 발생 하였습니다.");
            }
        } else {
            driverStatus.setGcmRegId("");
            driverStatus.setGcmRegId2("");
            driverStatusRepository.save(driverStatus);
        }

        return makeHeader(true);
    }
}

```

### `RestDocsSupport`
```
@ExtendWith(RestDocumentationExtension.class)
public abstract class RestDocsSupport {

    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(initController())
            .apply(documentationConfiguration(provider))
            .build();
    }

    protected abstract Object initController();
}
```

### `DriverControllerDocsTest`
```

public class DriverControllerDocsTest extends RestDocsSupport {

    private final DriverService driverService = mock(DriverService.class);

    @Override
    protected Object initController() {
        return new DriverController(driverService);
    }

    @DisplayName("기사 로그인 API")
    @Test
    void checkMatchLogin() throws Exception {
        //given
        given(driverService.driverLogin(
            anyInt(),
            anyString(),
            anyInt(),
            anyInt(),
            anyString(),
            anyString())).willReturn("true");

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
                preprocessResponse(prettyPrint()),
                formParameters(
                    parameterWithName("driverId").description("기사 ID"),
                    parameterWithName("password").description("비밀번호"),
                    parameterWithName("loginState").description("로그인 상태 - 1:로그인, 2:로그아웃"),
                    parameterWithName("deviceDiv").description("태블릿/휴대폰 구분 - 1:태블릿, 2:휴대폰"),
                    parameterWithName("regId").description("GCM Key")
                )
                //TODO DriverContoller의 리턴 값을 아래와 같이 JSON 타입으로 변경 후 적용할 것
//                responseFields(
//                    fieldWithPath("data").description("ID 존재 여부")
//                    fieldWithPath("code").type(JsonFieldType.NUMBER)
//                        .description("상태 코드"),
//                    fieldWithPath("message").type(JsonFieldType.STRING)
//                        .description("응답 메시지"),
//                    fieldWithPath("data").type(JsonFieldType.BOOLEAN)
//                        .description("ID 존재 여부")
//                )
            ));

    }
}
```
# 실행 방법
- Gradle > tasks > documentation > asciidoctor 실행
  ![[Pasted image 20240802114224.png]]

# 결과 확인
- `build/docs/asciidoc/index.html` 파일 확인
  ![[Pasted image 20240802115008.png]]

# Reference
- [공식 문서](https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/#documenting-your-api-query-parameters)
- [SpringBoot 3.x 적용](https://github.com/spring-projects/spring-restdocs/wiki/Spring-REST-Docs-3.0-Release-Notes#request-parameters-support-removed)
- [MockMvc 이해](https://adjh54.tistory.com/347)
- [RESTDocs tutorial](https://velog.io/@hwsa1004/Spring-Spring-RestDocs-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0#4-docs-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%BD%94%EB%93%9C-%EC%9E%91%EC%84%B1)

