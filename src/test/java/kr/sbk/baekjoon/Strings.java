package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Strings {
    /*
    https://www.acmicpc.net/problem/27866
    문제
단어
$S$와 정수
$i$가 주어졌을 때,
$S$의
$i$번째 글자를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 영어 소문자와 대문자로만 이루어진 단어
$S$가 주어진다. 단어의 길이는 최대
$1\,000$이다.

둘째 줄에 정수
$i$가 주어진다. (
$1 \le i \le \left|S\right|$)

출력
 
$S$의
$i$번째 글자를 출력한다.
     */
    public void whichPosition() throws Exception{

        try {
            String word = stringReadLine();

            int index = Integer.parseInt(stringReadLine());


            System.out.println(word.charAt(index - 1));
        } catch (NumberFormatException ignored) {

        }

    }

    private static String stringReadLine() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public void stringLength() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String t = br.readLine();

        System.out.println(t.length());
    }

    public void firstLast() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<String> words = new ArrayList<String>();

        for (int i = 0; i < n; i++) {
            words.add(br.readLine());
        }

        for (String word : words) {
            char start = word.charAt(0);
            char end = word.charAt(word.length() - 1);

            //char는 숫자처럼 연산되므로 조심
            System.out.println(""+start+end);
        }

    }
}
