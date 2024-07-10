package kr.sbk.baekjoon;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
