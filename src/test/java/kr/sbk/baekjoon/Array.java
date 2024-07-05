package kr.sbk.baekjoon;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/*
총 N개의 정수가 주어졌을 때, 정수 v가 몇 개인지 구하는 프로그램을 작성하시오.
*/
public class Array {

    @Test
    public void aMinusB() throws Exception{
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
            if ((int)e < Integer.parseInt(number[1])) System.out.print(e+" ");
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
    public static void minMax() throws Exception{

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
    public static void maxAndPosition(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> list = new ArrayList<>();
        for(int i=0;i<9;i++){

            String input = br.readLine();
            list.add(Integer.parseInt(input));
        }

        Optional<Integer> max = list.stream().max(Integer::compareTo);
        if(max.isPresent()){
            System.out.println(max.get());
        }
        System.out.println(list.indexOf(max.orElseThrow())+1);
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
