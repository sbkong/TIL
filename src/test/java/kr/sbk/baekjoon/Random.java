package kr.sbk.baekjoon;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
총 N개의 정수가 주어졌을 때, 정수 v가 몇 개인지 구하는 프로그램을 작성하시오.
*/
public class Random {

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
