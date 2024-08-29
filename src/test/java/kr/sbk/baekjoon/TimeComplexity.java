package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 시간 복잡도 계산 문제들.
 * 1. 다항식
 * 다항식은 여러 개의 항(숫자나 문자)들이 더해져 있는 수식을 말해. 예를 들어, 2x + 3 같은 식을 다항식이라고 해. 여기서 x는 변수라고 부르는데, 숫자 대신에 어떤 값이 들어갈 수 있는 자리야.
 * <p>
 * 2. 상수항
 * 상수항은 다항식에서 숫자만 있는 항을 말해. 예를 들어, 2x + 3에서 3이 상수항이야. 3은 어떤 숫자가 들어가든 항상 같은 값을 가지니까 상수라고 불러.
 * <p>
 * 3. O(1)
 * 이건 프로그래밍에서 시간 복잡도를 나타내는 특별한 기호야. 간단하게 말하면, 프로그램이 어떤 일을 처리하는 데 걸리는 시간이 항상 일정하다는 뜻이야. 예를 들어, 초콜릿을 하나 먹는 데 걸리는 시간이 아무리 많이 먹어도 똑같이 5초가 걸린다면, 그걸 O(1)이라고 할 수 있어.
 * <p>
 * 4. 최고차항
 * 다항식에서 가장 큰 '차수'를 가진 항을 최고차항이라고 해. 차수는 뒤에서 설명할 거야. 예를 들어, 2x^2 + 3x + 1이라는 다항식에서 2x^2이 최고차항이야, 왜냐하면 x^2가 가장 높은 차수(2차)를 가지고 있기 때문이야.
 * <p>
 * 5. 차수
 * 차수는 다항식에서 변수 x가 몇 번 곱해졌는지를 나타내는 숫자야. 예를 들어, x^2에서 2는 차수야. x가 2번 곱해졌다는 뜻이지. 만약 x^3이면 차수는 3이야. 차수가 높을수록 다항식은 더 복잡해져.
 */
public class TimeComplexity {

    /**
     * 튜토리얼
     *
     * <a href="https://www.acmicpc.net/problem/24262">...</a>
     * <p>
     * MenOfPassion(A[], n) {
     * i = ⌊n / 2⌋;
     * return A[i]; # 코드1
     * }
     * <p>
     * 위와 같은 함수가 있을 때 n을 입력하면 함수는 몇 번 호출되고, 수행 횟수를 다항식으로 표현하면이 문제인데,
     * n이 몇이든 1회 함수를 수행하고, -> 1
     * 수행 시간 또한 n이 몇이든 일정하다. -> O(1) : 0차 함수
     * <p>
     * 그래서 답은
     * 1
     * 0
     * 이다.
     */
    public void timeComplexity1() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(br.readLine());

            System.out.println(1);
            System.out.println(0);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 튜토리얼 2
     * <a href="https://www.acmicpc.net/problem/24263">...</a>
     * <p>
     * MenOfPassion(A[], n) {
     * sum <- 0;
     * for i <- 1 to n
     * sum <- sum + A[i]; # 코드1
     * return sum;
     * }
     * <p>
     * n번 수행하고 O(n) 이니까
     * <p>
     * n
     * 1
     */
    public void timeComplexity2() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(br.readLine());

            System.out.println(n);
            System.out.println(1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 시간 복잡도 3
     * <p>
     * MenOfPassion(A[], n) {
     * sum <- 0;
     * for i <- 1 to n
     * for j <- 1 to n
     * sum <- sum + A[i] × A[j]; # 코드1
     * return sum;
     * }
     * <p>
     * 이건 수행 횟수가 n^2
     * O(n^2) 차수는 2
     *
     * 50만*50만이 2500억 이므로 Long을 써야 한다.
     * (int는 -2147483648 ~ 21억 4748만 3647)
     */
    public void timeComplexity3() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            long n = Long.parseLong(br.readLine());

            System.out.println(n * n);
            System.out.println(2);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
