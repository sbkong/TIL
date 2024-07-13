package kr.sbk.baekjoon;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
총 N개의 정수가 주어졌을 때, 정수 v가 몇 개인지 구하는 프로그램을 작성하시오.
*/
public class Array {

    @Test
    public void aMinusB() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());

        List list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            list.add((int) (Math.random() * 100.0));
        }
        System.out.println(list.size());

        list.stream().forEach(e -> System.out.print(e + " "));
        System.out.println();

        br = new BufferedReader(new InputStreamReader(System.in));

        int target = Integer.parseInt(br.readLine());

        AtomicInteger result = new AtomicInteger();
        list.stream().forEach(e -> {
            if (e.equals(target)) result.set(result.get() + 1);
        });

        System.out.println(result);
    }

    /*
    문제
    정수 N개로 이루어진 수열 A와 정수 X가 주어진다. 이때, A에서 X보다 작은 수를 모두 출력하는 프로그램을 작성하시오.

    입력
    첫째 줄에 N과 X가 주어진다. (1 ≤ N, X ≤ 10,000)

    둘째 줄에 수열 A를 이루는 정수 N개가 주어진다. 주어지는 정수는 모두 1보다 크거나 같고, 10,000보다 작거나 같은 정수이다.

    출력
    X보다 작은 수를 입력받은 순서대로 공백으로 구분해 출력한다. X보다 작은 수는 적어도 하나 존재한다.
     */
    public static void betterThen() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        String number[] = input.split("\\s");

        List list = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(number[0]); i++) {
            list.add((int) (Math.random() * 10000.0));
        }

        list.stream().forEach(e -> System.out.print(e + " "));
        System.out.println();

        list.stream().forEach(e -> {
            if ((int) e < Integer.parseInt(number[1])) System.out.print(e + " ");
        });

        /***
         input
         10 5000

         output
         8470 2438 5984 889 2421 9328 5613 1176 923 7151
         2438 889 2421 1176 923
         */
    }

    /*
    문제
N개의 정수가 주어진다. 이때, 최솟값과 최댓값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정수의 개수 N (1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄에는 N개의 정수를 공백으로 구분해서 주어진다. 모든 정수는 -1,000,000보다 크거나 같고, 1,000,000보다 작거나 같은 정수이다.

출력
첫째 줄에 주어진 정수 N개의 최솟값과 최댓값을 공백으로 구분해 출력한다.
     */
    public static void minMax() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int number = Integer.parseInt(input);
        Random rand = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(rand.nextInt(2000001) - 1000000);
        }
        list.stream().forEach(e -> System.out.print(e + " "));
        System.out.println();

        list.sort((o1, o2) -> o1.compareTo(o2));

        System.out.print(list.get(0) + " " + list.get(list.size() - 1));

    }

    /*
    https://www.acmicpc.net/problem/2562
    문제
9개의 서로 다른 자연수가 주어질 때, 이들 중 최댓값을 찾고 그 최댓값이 몇 번째 수인지를 구하는 프로그램을 작성하시오.

예를 들어, 서로 다른 9개의 자연수

3, 29, 38, 12, 57, 74, 40, 85, 61

이 주어지면, 이들 중 최댓값은 85이고, 이 값은 8번째 수이다.

입력
첫째 줄부터 아홉 번째 줄까지 한 줄에 하나의 자연수가 주어진다. 주어지는 자연수는 100 보다 작다.

출력
첫째 줄에 최댓값을 출력하고, 둘째 줄에 최댓값이 몇 번째 수인지를 출력한다.
     */
    public static void maxAndPosition() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {

            String input = br.readLine();
            list.add(Integer.parseInt(input));
        }

        Optional<Integer> max = list.stream().max(Integer::compareTo);
        if (max.isPresent()) {
            System.out.println(max.get());
        }
        System.out.println(list.indexOf(max.orElseThrow()) + 1);
    }

    /*
    문제
세준이는 기말고사를 망쳤다. 세준이는 점수를 조작해서 집에 가져가기로 했다. 일단 세준이는 자기 점수 중에 최댓값을 골랐다. 이 값을 M이라고 한다. 그리고 나서 모든 점수를 점수/M*100으로 고쳤다.

예를 들어, 세준이의 최고점이 70이고, 수학점수가 50이었으면 수학점수는 50/70*100이 되어 71.43점이 된다.

세준이의 성적을 위의 방법대로 새로 계산했을 때, 새로운 평균을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 시험 본 과목의 개수 N이 주어진다. 이 값은 1000보다 작거나 같다. 둘째 줄에 세준이의 현재 성적이 주어진다. 이 값은 100보다 작거나 같은 음이 아닌 정수이고, 적어도 하나의 값은 0보다 크다.

출력
첫째 줄에 새로운 평균을 출력한다. 실제 정답과 출력값의 절대오차 또는 상대오차가 10-2 이하이면 정답이다.
     */
    public static void getFakeScore() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");

        List<Integer> list = new ArrayList<>();

        Arrays.stream(input).forEach(x -> list.add(Integer.parseInt(x)));
        list.sort(Collections.reverseOrder());

        int max = list.getFirst();

        double sum=0;
        for (Integer integer : list) {
            sum += (double) integer / max * 100;
        }
        System.out.println(sum/list.size());

        br.close();
    }

    /*
https://www.acmicpc.net/problem/10811

문제
도현이는 바구니를 총 N개 가지고 있고, 각각의 바구니에는 1번부터 N번까지 번호가 순서대로 적혀져 있다. 바구니는 일렬로 놓여져 있고, 가장 왼쪽 바구니를 1번째 바구니, 그 다음 바구니를 2번째 바구니, ..., 가장 오른쪽 바구니를 N번째 바구니라고 부른다.

도현이는 앞으로 M번 바구니의 순서를 역순으로 만들려고 한다. 도현이는 한 번 순서를 역순으로 바꿀 때, 순서를 역순으로 만들 범위를 정하고, 그 범위에 들어있는 바구니의 순서를 역순으로 만든다.

바구니의 순서를 어떻게 바꿀지 주어졌을 때, M번 바구니의 순서를 역순으로 만든 다음, 바구니에 적혀있는 번호를 가장 왼쪽 바구니부터 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 N (1 ≤ N ≤ 100)과 M (1 ≤ M ≤ 100)이 주어진다.

둘째 줄부터 M개의 줄에는 바구니의 순서를 역순으로 만드는 방법이 주어진다. 방법은 i j로 나타내고, 왼쪽으로부터 i번째 바구니부터 j번째 바구니의 순서를 역순으로 만든다는 뜻이다. (1 ≤ i ≤ j ≤ N)

도현이는 입력으로 주어진 순서대로 바구니의 순서를 바꾼다.

출력
모든 순서를 바꾼 다음에, 가장 왼쪽에 있는 바구니부터 바구니에 적혀있는 순서를 공백으로 구분해 출력한다.
     */
    public void reverseBasket() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        List<Integer> list = new ArrayList<>();

        list.forEach(System.out::println);

        for (int i = 1; i <= Integer.parseInt(input[0]); i++) {
            list.add(i);
        }

        for (int i = 1; i <= Integer.parseInt(input[1]); i++) {
            String[] reverseBasket = br.readLine().split(" ");
            int start = Integer.parseInt(reverseBasket[0])-1;
            int end = Integer.parseInt(reverseBasket[1])-1;

            for (int j = start; j < end; j++, end--) {
                int tmp = list.get(j);
                list.set(j, list.get(end));
                list.set(end, tmp);
            }
            /* Collection.reverse 사용
            if (start >= 0 && end <= list.size()) {
                Collections.reverse(list.subList(start, end));
            }
             */
        }
        list.forEach(e->System.out.print(e + " "));
        br.close();
    }

    /*
    https://www.acmicpc.net/problem/3052
    문제
두 자연수 A와 B가 있을 때, A%B는 A를 B로 나눈 나머지 이다. 예를 들어, 7, 14, 27, 38을 3으로 나눈 나머지는 1, 2, 0, 2이다.

수 10개를 입력받은 뒤, 이를 42로 나눈 나머지를 구한다. 그 다음 서로 다른 값이 몇 개 있는지 출력하는 프로그램을 작성하시오.

입력
첫째 줄부터 열번째 줄 까지 숫자가 한 줄에 하나씩 주어진다. 이 숫자는 1,000보다 작거나 같고, 음이 아닌 정수이다.

출력
첫째 줄에, 42로 나누었을 때, 서로 다른 나머지가 몇 개 있는지 출력한다.
     */
    public static void sameRemainder() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Integer.parseInt(br.readLine()) % 42);
        }
        List<Integer> result = list.stream().distinct().collect(Collectors.toList());
//        List<Integer> result = list.stream().distinct().toList(); // Collection 을 왼쪽과 같이 간소화 가능

//        Set<Integer> uniqueElements = new HashSet<>(); //Java 8 미만에서는 이렇게 중복 제거

        /* 5
        // 함수를 쓰지 않으려면 아래와 같이 작성
        List<Integer> distinct = new ArrayList<>();
        for (Integer element : list) {
            if(distinct.contains(element)) distinct.add(element);
        }
        */

//        result.forEach(e -> System.out.print(e + " "));
//        System.out.println();
        System.out.println(result.size());
    }

    /*
    https://www.acmicpc.net/problem/5597
    문제
X대학 M교수님은 프로그래밍 수업을 맡고 있다. 교실엔 학생이 30명이 있는데, 학생 명부엔 각 학생별로 1번부터 30번까지 출석번호가 붙어 있다.

교수님이 내준 특별과제를 28명이 제출했는데, 그 중에서 제출 안 한 학생 2명의 출석번호를 구하는 프로그램을 작성하시오.

입력
입력은 총 28줄로 각 제출자(학생)의 출석번호 n(1 ≤ n ≤ 30)가 한 줄에 하나씩 주어진다. 출석번호에 중복은 없다.

출력
출력은 2줄이다. 1번째 줄엔 제출하지 않은 학생의 출석번호 중 가장 작은 것을 출력하고, 2번째 줄에선 그 다음 출석번호를 출력한다.
     */
    public static void noSubmitPaper() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = 0;
        List<Integer> studentNumber = new ArrayList<>();
        List<Integer> originalStudentNumber = new ArrayList<>();

        do {
            studentNumber.add(Integer.parseInt(br.readLine()));
            n++;
        } while (n < 28);

        for (int i = 1; i <= 30; i++) {
            originalStudentNumber.add(i);
        }

        for(Integer student : originalStudentNumber) {
            if(!studentNumber.contains(student)) System.out.println(student);
        }

/*      //이렇게도 가능할 듯
        // originalStudentNumber에서 studentNumber에 포함된 번호들 제거
        originalStudentNumber.removeAll(studentNumber);
*/

        /* //Stream 사용

        Scanner scanner = new Scanner(System.in);

        // 학생 번호 입력 받기
        System.out.println("28명의 학생 번호를 입력하세요:");
        List<Integer> studentNumbers = IntStream.range(0, 28)
                                                .mapToObj(i -> scanner.nextInt())
                                                .collect(Collectors.toList());

        // 1부터 30까지의 번호를 originalStudentNumbers에 추가
        List<Integer> originalStudentNumbers = IntStream.rangeClosed(1, 30)
                                                       .boxed()
                                                       .collect(Collectors.toList());

        // studentNumbers에 포함되지 않은 번호들만 필터링하여 출력
        System.out.println("결과:");
        originalStudentNumbers.stream()
                              .filter(num -> !studentNumbers.contains(num))
                              .forEach(System.out::println);
         */

    }

    /*
    https://www.acmicpc.net/problem/10813
    문제
도현이는 바구니를 총 N개 가지고 있고, 각각의 바구니에는 1번부터 N번까지 번호가 매겨져 있다. 바구니에는 공이 1개씩 들어있고, 처음에는 바구니에 적혀있는 번호와 같은 번호가 적힌 공이 들어있다.

도현이는 앞으로 M번 공을 바꾸려고 한다. 도현이는 공을 바꿀 바구니 2개를 선택하고, 두 바구니에 들어있는 공을 서로 교환한다.

공을 어떻게 바꿀지가 주어졌을 때, M번 공을 바꾼 이후에 각 바구니에 어떤 공이 들어있는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N (1 ≤ N ≤ 100)과 M (1 ≤ M ≤ 100)이 주어진다.

둘째 줄부터 M개의 줄에 걸쳐서 공을 교환할 방법이 주어진다. 각 방법은 두 정수 i j로 이루어져 있으며, i번 바구니와 j번 바구니에 들어있는 공을 교환한다는 뜻이다. (1 ≤ i ≤ j ≤ N)

도현이는 입력으로 주어진 순서대로 공을 교환한다.

출력
1번 바구니부터 N번 바구니에 들어있는 공의 번호를 공백으로 구분해 출력한다.
     */
    public static void swapBall() throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strNM = br.readLine().split(" ");

        int n = Integer.parseInt(strNM[0]);
        int m = Integer.parseInt(strNM[1]);

        List<Integer> basket = IntStream.range(1, n + 1).boxed().collect(Collectors.toList());

        for (int i = 0; i < m; i++) {
            String[] swapNumber = br.readLine().split(" ");

            int first = Integer.parseInt(swapNumber[0]) - 1;
            int second = Integer.parseInt(swapNumber[1]) - 1;

            int tmp = basket.get(first);
            basket.set(first, basket.get(second));
            basket.set(second, tmp);

            /*
                int tmp = basket.get(first);
                int tmp2 = basket.get(second);

                basket.remove(first);
                basket.add(first, tmp2);

                basket.remove(second);
                basket.add(second, tmp);
             */
        }
        basket.forEach(e -> System.out.print(e + " "));

    }

    /*
    https://www.acmicpc.net/problem/10810
    문제
도현이는 바구니를 총 N개 가지고 있고, 각각의 바구니에는 1번부터 N번까지 번호가 매겨져 있다. 또, 1번부터 N번까지 번호가 적혀있는 공을 매우 많이 가지고 있다. 가장 처음 바구니에는 공이 들어있지 않으며, 바구니에는 공을 1개만 넣을 수 있다.

도현이는 앞으로 M번 공을 넣으려고 한다. 도현이는 한 번 공을 넣을 때, 공을 넣을 바구니 범위를 정하고, 정한 바구니에 모두 같은 번호가 적혀있는 공을 넣는다. 만약, 바구니에 공이 이미 있는 경우에는 들어있는 공을 빼고, 새로 공을 넣는다. 공을 넣을 바구니는 연속되어 있어야 한다.

공을 어떻게 넣을지가 주어졌을 때, M번 공을 넣은 이후에 각 바구니에 어떤 공이 들어 있는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N (1 ≤ N ≤ 100)과 M (1 ≤ M ≤ 100)이 주어진다.

둘째 줄부터 M개의 줄에 걸쳐서 공을 넣는 방법이 주어진다. 각 방법은 세 정수 i j k로 이루어져 있으며, i번 바구니부터 j번 바구니까지에 k번 번호가 적혀져 있는 공을 넣는다는 뜻이다. 예를 들어, 2 5 6은 2번 바구니부터 5번 바구니까지에 6번 공을 넣는다는 뜻이다. (1 ≤ i ≤ j ≤ N, 1 ≤ k ≤ N)

도현이는 입력으로 주어진 순서대로 공을 넣는다.

출력
1번 바구니부터 N번 바구니에 들어있는 공의 번호를 공백으로 구분해 출력한다. 공이 들어있지 않은 바구니는 0을 출력한다.
     */
    public static void inputBall() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] init = br.readLine().split(" ");

        int basketSize = Integer.parseInt(init[0]);
        int loop = Integer.parseInt(init[1]);

        List<Integer> basket = IntStream.range(0, basketSize).mapToObj(i -> 0).collect(Collectors.toList());

        for (int i = 0; i < loop; i++) {
            String[] setBall = br.readLine().split(" ");

            int start = Integer.parseInt(setBall[0]) - 1;
            int end = Integer.parseInt(setBall[1]) - 1;
            int ballNumber = Integer.parseInt(setBall[2]);

            for (int j = start; j <= end; j++) {
                basket.set(j, ballNumber);
            }
        }
        basket.forEach(e -> System.out.print(e + " "));
    }
}

/*
랜덤 함수는 랜덤이 아니다?
컴퓨터는 입력한 값을 계산하는 계산기인데, 랜덤이란 있을 수 없다.

랜덤 함수는 브라우저에 따라서도 다르게 나타나며 여러가지 알고리즘으로 수행했다.
그런데 Crypto-js에서 취약점이 발견되어 crypto-js도 더 고도의 난수 알고리즘을 사용한단다.

즉, random은 난수인 척 하는 함수인 것이다.

아래 방법으로 해결한다.

Browser
window.crypto.getRandomValues

Node.js
require('crypto').randomBytes

-reference : https://jungpaeng.tistory.com/59
 */
