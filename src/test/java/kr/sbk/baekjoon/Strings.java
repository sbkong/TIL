package kr.sbk.baekjoon;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void whichPosition() throws Exception {

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
            System.out.println("" + start + end);
        }
    }

    public void sumEachOther() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String c = br.readLine();

        if (c.length() != n) System.exit(0);

        String[] word = c.split("");
        int sum = 0;
        for (String w : word) {
            sum += Integer.parseInt(w);
        }

        System.out.println(sum);

    }

    public void getPositionOfAlphabet() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] charArray = br.readLine().toCharArray();
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {

            int position = -1;
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == alphabet) {
                    position = i;
                    break;
                }
            }
            System.out.print(position + " ");
        }
    }

    public void repeatWords() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");

            int copyCnt = Integer.parseInt(str[0]);
            char[] Alphabet = str[1].toCharArray();

            for (char ap : Alphabet) {
                for (int j = 0; j < copyCnt; j++) {
                    System.out.print(ap);
                }
            }
            System.out.println();
        }
    }

    public void repeatWords2() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder[] sb = new StringBuilder[n];
        for (int i = 0; i < n; i++) {
            sb[i] = new StringBuilder();
            String[] str = br.readLine().split(" ");

            int copyCnt = Integer.parseInt(str[0]);
            char[] Alphabet = str[1].toCharArray();

            for (char ap : Alphabet) {
                for (int j = 0; j < copyCnt; j++) {
                    sb[i].append(ap);
                }
            }
        }

        for (StringBuilder s : sb) {
            System.out.println(s);
        }
    }

    public void countWords() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] words = br.readLine().split(" ");


        String[] nonEmptyWords = Arrays.stream(words).filter(word -> !"".equals(word)).toArray(String[]::new);

        System.out.println(nonEmptyWords.length);

    }

    @Test
    public void reverseWords() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        Integer[] numbers = new Integer[input.length];

        int j = 0;
        for (String number : input) {
            char[] reverse = number.toCharArray();
            String s = "";
            for (int i = reverse.length - 1; i >= 0; i--) {
                s += reverse[i];
            }
            numbers[j] = Integer.valueOf(s);
            j++;
        }

        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (max < numbers[i]) {
                max = numbers[i];
            }
        }
        System.out.println(max);

    }


}
