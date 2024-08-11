# 현상
- @RestControllerAdvice 어노테이션으로 리턴 값을 재조정 하는 경우, Mock test에서는 조정 전 값을 리턴한다.

> e.g. Return
```
# real
{
    "code": "0000",
    "message": "정상",
    "data": "OK"
}

# mock test
"OK"
```

- 위 경우 `mvcMock`을 선언하는 부분에서 @RestControllerAdvice 클래스를 사용하도록 해야 한다.
```
# before
@BeforeEach  
void setUp(RestDocumentationContextProvider provider) {  
    this.mockMvc = MockMvcBuilders.standaloneSetup(initController())
        .apply(documentationConfiguration(provider))  
        .build();  
  
}

# after
@BeforeEach  
void setUp(RestDocumentationContextProvider provider) {  
    this.mockMvc = MockMvcBuilders.standaloneSetup(initController())
    .setControllerAdvice(ApiResponseWrappingAdvisor.class)
        .apply(documentationConfiguration(provider))  
        .build();  
  
}
```

# 전체 코드
### DriverAuthControllerDocsTest
```
@DisplayName("기사 인증")  
public class DriverAuthControllerDocsTest extends RestDocsSupport {  
  
    private final DriverAuthService driverAuthService = mock(DriverAuthService.class);  
  
    @Override  
    protected Object initController() {  
        return new DriverAuthController(driverAuthService);  
    }
@DisplayName("단말기 확인")  
@Test  
void checkPhoneFailure() throws Exception {  
    String phoneNo = "01074623940";  
  
    //given  
    given(driverAuthService.checkPhoneNo(  
        anyString())).willReturn(ReturnCode.OK);  
  
    mockMvc.perform(  
            // when  
            RestDocumentationRequestBuilders.get("/api/driver/auth/phone/check")  
                .param("stPhoneNo", phoneNo)  
        )  
  
        // then  
        .andDo(print())  
        .andExpect(status().isOk())  
        .andExpect(MockMvcResultMatchers.content()  
            .string("{\"code\":\"0000\",\"message\":\"정상\",\"data\":\"OK\"}"))  
        .andDo(document("driver-check-phone-failure",  
            preprocessRequest(prettyPrint()),  
            preprocessResponse(prettyPrint()),  
            resource(ResourceSnippetParameters.builder()  
                .tag("Driver API")  
                .summary("단말기 정보 조회")  
                .formParameters(  
                    parameterWithName("stPhoneNo").description("단말기 전화번호")  
                )  
                .responseFields(  
                    fieldWithPath("code").type(STRING).description("코드값"),  
                    fieldWithPath("message").type(STRING).description("메시지"),  
                    fieldWithPath("data").type(STRING).description("결과데이터")  
                )  
                .requestSchema(Schema.schema("FormParameter-driver-check-phone-failure"))  
                .responseSchema(Schema.schema("UserResponse.PhoneCheck"))  
                .build())));  
}
```

### RestDocsSupport
```
@DisplayName("단말기 확인")  
@Test  
void checkPhoneFailure() throws Exception {  
    String phoneNo = "01074623940";  
  
    //given  
    given(driverAuthService.checkPhoneNo(  
        anyString())).willReturn(ReturnCode.OK);  
  
    mockMvc.perform(  
            // when  
            RestDocumentationRequestBuilders.get("/api/driver/auth/phone/check")  
                .param("stPhoneNo", phoneNo)  
        )  
  
        // then  
        .andDo(print())  
        .andExpect(status().isOk())  
        .andExpect(MockMvcResultMatchers.content()  
            .string("{\"code\":\"0000\",\"message\":\"정상\",\"data\":\"OK\"}"))  
        .andDo(document("driver-check-phone-failure",  
            preprocessRequest(prettyPrint()),  
            preprocessResponse(prettyPrint()),  
            resource(ResourceSnippetParameters.builder()  
                .tag("Driver API")  
                .summary("단말기 정보 조회")  
                .formParameters(  
                    parameterWithName("stPhoneNo").description("단말기 전화번호")  
                )  
                .responseFields(  
                    fieldWithPath("code").type(STRING).description("코드값"),  
                    fieldWithPath("message").type(STRING).description("메시지"),  
                    fieldWithPath("data").type(STRING).description("결과데이터")  
                )  
                .requestSchema(Schema.schema("FormParameter-driver-check-phone-failure"))  
                .responseSchema(Schema.schema("UserResponse.PhoneCheck"))  
                .build())));  
}
```

### ApiResponseWrappingAdvisor
```
/**  
 * 웹요청 처리결과 응답값을 봉투패턴(Envelop pattern)으로 일정한 데이터 형식으로 가공  
 * <pre>  
 * { *     "code": "0000", *     "message": "OK", *     "data": { data } * } * </pre>  
 */@Slf4j  
@RestControllerAdvice(basePackages = {  
    "org.kbucall.databridge.app.controller",  
})  
public class ApiResponseWrappingAdvisor implements ResponseBodyAdvice<Object> {  
  
    private Type getGenericType(MethodParameter returnType) {  
        if (HttpEntity.class.isAssignableFrom(returnType.getParameterType())) {  
            return ResolvableType.forType(returnType.getGenericParameterType()).getGeneric()  
                .getType();  
        } else {  
            return returnType.getGenericParameterType();  
        }  
    }  
  
    @Override  
    public boolean supports(MethodParameter returnType,  
        Class<? extends HttpMessageConverter<?>> converterType) {  
        Type type = GenericTypeResolver.resolveType(getGenericType(returnType),  
            returnType.getContainingClass());  
  
        if (returnType.getExecutable().getName().toUpperCase().contains("HEALTH")) { // healthCheck  
            return false;  
        }  
  
        if (Void.class.getName().equals(type.getTypeName())) {  
            return false;  
        }  
  
        return !converterType.isAssignableFrom(ByteArrayHttpMessageConverter.class) &&  
            !converterType.isAssignableFrom(ResourceHttpMessageConverter.class);  
    }  
  
    @Override  
    public Object beforeBodyWrite(@Nullable Object body,  
        @NonNull MethodParameter returnType,  
        @NonNull MediaType selectedContentType,  
        @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,  
        @NonNull ServerHttpRequest request,  
        @NonNull ServerHttpResponse response) {  
  
        HttpStatus responseStatus = HttpStatus.valueOf(  
            ((ServletServerHttpResponse) response).getServletResponse().getStatus()  
        );  
  
        if (Objects.isNull(body)) {  
            return responseStatus.isError() ? ApiResponseGenerator.fail()  
                : ApiResponseGenerator.success();  
        }  
  
        var apiResponse = responseStatus.isError() ? ApiResponseGenerator.fail(body)  
            : ApiResponseGenerator.success(body);  
        log.trace("[ApiResponse] {}", apiResponse);  
  
        if (selectedConverterType.isAssignableFrom(StringHttpMessageConverter.class)) {  
            try {  
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);  
                return JsonUtils.toJson(apiResponse);  
            } catch (JsonUtils.JsonEncodeException jpe) {  
                log.warn("JSON 처리 중 오류 발생", jpe);  
                throw new ApiResponseJsonProcessingException(jpe);  
            }  
        }  
  
        return apiResponse;  
    }  
}
```

# Reference
- https://stackoverflow.com/questions/64841599/how-to-call-restcontrolleradvice-class-from-a-unit-test-in-the-service-layer-of
  \
