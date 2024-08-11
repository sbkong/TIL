- 백준 문제를 푸는 중 특정 알고리즘을 람다 함수로 풀고 싶었다. 하지만 람다 외부에 있는 int를 변경할 수 없는 문제에 직면했다. 아래는 해당 코드 중 일부이다.
```

int n = new AtomicInteger(Integer.parseInt(br.readLine()));

// 정수 n의 소수 찾기  
List<Integer> primeNumbers = new ArrayList<>();  
for (int i = 2; i <= n.get(); i++) {  
    if (n.get() % i == 0) {  
        primeNumbers.add(i);  
    }  
}  
  
primeNumbers.forEach(  
        e -> {  
            while (n.get() % e == 0) {  
                System.out.println(e);  
                n.updateAndGet(v -> v / e);  
            }  
        }  
);
```
> [!error]
> Variable used in lambda expression should be final or effectively final

- final로 정의하던지 하란다...

# Lambda 함수 내부에서 외부의 정수 값 변경하기
- Lambda 함수 내부에서 외부의 int 값을 변경하는 것이 불가능하다.

### AtomicInteger
- 멀티스레드 환경에서 변수의 값을 안전하게 변경할 수 있다.
- 하지만 코드가 읽기 힘들어지는 단점이 있다. (복잡성)
### 결론
- 멀티 스레드 환경이 아니라면 for loop를 이용하는 것이 코드를 읽기 쉽다.

# 개선된 코드
```
int n = Integer.parseInt(br.readLine());

// 정수 n의 소수 찾기
List<Integer> primeNumbers = new ArrayList<>();
for (int i = 2; i <= n; i++) { // 1은 소수가 아니므로 2부터 시작
    if (n % i == 0) {
        primeNumbers.add(i);
    }
}

// n의 소인수들을 출력하고, n을 나눔
for (int i = 0; i < primeNumbers.size(); i++) {
    int prime = primeNumbers.get(i);
    while (n % prime == 0) {
        System.out.println(prime);
        n /= prime;
    }
}

```
# Reference
`AtomicInteger`를 사용하면 스레드 안전하게 변수를 변경할 수 있는 장점이 있습니다. 하지만 이 장점은 멀티스레딩 환경에서만 유효합니다. 단일 스레드 환경에서는 `AtomicInteger`를 사용하는 것이 오히려 불필요하게 복잡성을 추가할 수 있습니다.

여기서 `AtomicInteger`의 사용이 적절한지 판단하기 위해 다음 사항을 고려해야 합니다:

1. **멀티스레드 환경인지 여부**: 만약 코드를 멀티스레드 환경에서 실행 중이라면 `AtomicInteger`는 변수 `n`의 값을 안전하게 변경할 수 있도록 도와줍니다. 그러나 단일 스레드에서 실행되는 프로그램이라면 굳이 `AtomicInteger`를 사용할 필요는 없습니다.

2. **부수 효과 없는 람다식 사용**: 람다식 내부에서 외부 변수를 변경하려면 일반적으로 그 변수가 `final` 또는 `effectively final`이어야 합니다. `AtomicInteger`를 사용하면 이 제한을 피할 수 있지만, 이렇게 할 필요가 없다면 간단한 코드가 더 바람직할 수 있습니다.

3. **코드 가독성**: `AtomicInteger`는 다소 직관적이지 않아서, 동시성 문제가 없는데도 불구하고 사용하면 코드를 이해하기 어려울 수 있습니다. 단일 스레드 환경에서는 코드 가독성을 위해 그냥 `int`를 사용하는 것이 더 나을 수 있습니다.

### 결론
`AtomicInteger`는 멀티스레드 환경에서 변수의 안전한 변경이 필요할 때 유용합니다. 그러나 단일 스레드 환경이나, 동시성 문제가 없는 상황에서는 굳이 사용할 필요가 없습니다. 일반적인 상황에서는 코드가 간결하고 이해하기 쉬운 방법을 선택하는 것이 좋습니다.

위의 예제 코드에서는 멀티스레드와는 관련이 없으므로 `AtomicInteger`를 사용하지 않고, `int`로 `n`을 직접 변경하는 것이 더 좋습니다.