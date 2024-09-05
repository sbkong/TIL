# Lighthouse
웹 화면 성능 평가 도구, 구글에서 개발함
- 크롬 Extension으로 추가 가능
- 크롬 개발자 도구에 포함되어있음
- npm으로 다운로드 가능(리눅스)
- [Lighthouse-CI](https://github.com/GoogleChrome/lighthouse-ci) : CLI에서 다른 사이트 측정 가능

## Lighthouse로 확인할 수 있는 지표

Lighthouse는 성능을 측정하기 위해서 다음의 메트릭을 사용한다.  
(이외에도 부가 항목이 존재하지만 자세한 설명은 생략한다. [다음 링크](https://web.dev/lighthouse-performance/#metrics)에서 확인할 수 있다.)

1. First Contentful Paint
    - FCP
    - 사용자가 특정 웹 페이지로 이동했을 때, 브라우저가 첫 번째 DOM의 콘텐츠를 렌더링하는 데 걸리는 시간
2. First Meaningful Paint
    - FMP
    - 사용자가 페이지를 불러오기 시작하면서 스크롤을 내리지 않은 채 제일 먼저 볼 수 있는 영역에 존재하는 주요한 콘텐츠를 렌더링하는 데 걸리는 시간
    - Lighthouse 버전 6.0 이후로 사용되지 않는다. (작은 차이에도 매우 민감하게 측정되어 일관성 없는 결과를 초래하였기 때문이다.)
3. Speed Index
    - 웹 페이지를 불러올 때, 콘텐츠가 시각적으로 표시되는 데까지 걸리는 시간
4. First CPU Idle
    - 웹 페이지가 최소한으로 상호작용할 수 있는 상태가 될 때까지 걸리는 시간
    - Lighthouse 버전 6.0 이후로 사용되지 않는다. (Time To Interactive와 측정 기준이 유사했지만 의미 있는 결과값을 보이지 못했다.)
5. Time To Interactive
    - 웹 페이지가 완전히 상호작용할 수 있는 상태가 될 때까지 걸리는 시간
6. Max Potential First Input Delay
    - FID
    - 사용자가 웹 사이트와 처음 상호작용(버튼 클릭)할 때부터 브라우저가 실제로 해당 상호작용에 응답할 수 있을 때까지 걸리는 가장 긴 시간
    - (즉, 최악의 경우를 측정)
7. Total Blocking Time
    - TBT
    - 웹 페이지가 사용자 입력에 응답하지 못하도록 차단된 총 시간
    - = 로딩 중 메인 스레드가 긴 시간동안 중단되어 응답을 받을 수 없을 정도로 걸린 시간
8. Largest Contentful Paint
    - LCP
    - 뷰포트에서 가장 큰 콘텐츠 요소가 화면에 렌더링 될 때까지 걸리는 시간

서술한 건 8개지만 Lighthouse 버전 6.0부터 사용되지 않는 메트릭를 제외하면 총 6개라고 볼 수 있다.  
그렇지만 이게 전부가 아니다. 여기에서 하나 더 추가된 지표가 있다. 그건 바로 Cumulative Layout Shift(CLS) 메트릭이다.

9. Cumulative Layout Shift
    - CLS
    - 이미지/광고의 느린 로딩, 비동기 동작, 동적 DOM 변경 등으로 웹 페이지의 레이아웃이 얼마나 변하는 지 측정한 값
    - 사용자가 잘못된 클릭을 하도록 유발하는 시각적 불안정성을 체크하는 지표

## Lighthouse 결과 항목 분석

#### 1. Performance

Lighthouse는 실제 속도가 어떻든 간에, 화면에 콘텐츠가 얼마나 빨리 표시되고 사용자는 얼마나 빠르게 해당 콘텐츠를 인식하는 지에 더욱 초점을 맞추고 있다.  
참고 : [Lighthouse Performance Scoring](https://web.dev/performance-scoring/)

- Lighthouse 6

|Audit|Weight|
|---|---|
|First Contentful Paint|15%|
|Speed Index|15%|
|Largest Contentful Paint|25%|
|Time to Interactive|15%|
|Total Blocking Time|25%|
|Cumulative Layout Shift|5%|

- Lighthouse 5

|Audit|Weight|
|---|---|
|First Contentful Paint|20%|
|Speed Index|27%|
|First Meaningful Paint|7%|
|Time to Interactive|33%|
|First CPU Idle|13%|

#### 2. Accessibility

Lighthouse는 웹 애플리케이션의 접근성을 검사한다. `<img>` 태그에 `alt` 속성이 있는지, `<html>` 태그에 `lang` 속성이 있는지, 배경색과 전경색의 대비가 충분한지와 같은 항목을 확인한다.

참고 : [Lighthouse Accessibility Scoring](https://web.dev/accessibility-scoring/)  
참고2 : [접근성이란?](https://developers.google.com/web/fundamentals/accessibility)

#### 3. Best Practices

Lighthouse는 웹 페이지가 웹에 대한 표준 모범 사례를 따르고 있는지 확인한다. 웹 애플리케이션을 가동할 때 콘솔에 오류가 출력되진 않는지, 더는 사용하지 않는 API를 호출하고 있지 않은지, HTTPS를 통해 해당 페이지에 접근할 수 있는지와 같은 항목을 확인한다.

참고 : [Best Practices Audits](https://web.dev/lighthouse-best-practices/)

#### 4. SEO

Lighthouse는 웹 페이지가 검색 엔진에 대해 최적화된 순위 결과를 가지고 있는지 확인한다. 각 사용자가 자신의 디바이스를 이용하여 웹 페이지에 접근하였을 때 그들이 콘텐츠를 읽는 데에 무리가 없는 글꼴 크기를 사용하는지, 웹 페이지의 robots.txt 파일이 유효한지, 올바른 상태 코드를 사용하는 지와 같은 일부 SEO 모범 사례를 확인한다.

참고 : [Webmaster Guidelines](https://developers.google.com/search/docs/advanced/guidelines/webmaster-guidelines)

#### 5. Progressive Web App

Lighthouse는 Progressive Web App을 정의하는 일련의 기준에 따라 웹 페이지를 확인한다. 이 검사는 해당 웹 페이지가 항목을 따르고 있는지 측정하여 점수를 부여하는 것이 아니다. 웹이 HTTP를 HTTPS로 리다이렉션하는지, 응답 코드는 명확한지, 3G 네트워크에서도 로딩이 빠르게 이루어지는 지와 같은 여부를 검사하여 합격 또는 실패를 부여한다.

참고 : [What makes a good Progressive Web App?](https://web.dev/pwa-checklist/)


# See also
- https://web.dev/ : 웹 최적화 논의
# Reference
- https://velog.io/@dell_mond/Lighthouse-%EC%82%AC%EC%9A%A9%EB%B2%95
- https://velog.io/@khy226/Lighthouse%EB%A1%9C-%EC%9B%B9-%EC%84%B1%EB%8A%A5-%EC%B8%A1%EC%A0%95%ED%95%98%EA%B3%A0-%EA%B0%9C%EC%84%A0%ED%95%98%EA%B8%B0
- https://velog.io/@edie_ko/lighthouse-performance
- https://velog.io/@greencloud/%EC%8C%88%EB%BD%95%ED%95%98%EA%B2%8C-Lighthouse-%EC%9E%90%EB%8F%99%ED%99%94%ED%95%98%EA%B8%B0-ijcqk0uv
