package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Devisor and multiple and prime number.
 * 약수, 배수, 소수에 관한 풀이
 */
public class DivisorAndMultipleAndPrimeNumber {
    /**
     * Divisor and multiplier.
     * <a href="https://www.acmicpc.net/problem/5086">...</a>
     */
    public void divisorAndMultiplier() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {

            String input;
            while (true) {
                input = br.readLine();
                if ("0 0".equals(input)) break;

                String[] numbers = input.split(" ");
                int first = Integer.parseInt(numbers[0]);
                int second = Integer.parseInt(numbers[1]);

                if (second % first == 0) System.out.println("factor");
                else if (first % second == 0) System.out.println("multiple");
                else System.out.println("neither");

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
        }
    }

    /**
     * Gets divisor by n.
     * <p>
     * https://www.acmicpc.net/problem/2501
     */
    public void getDivisorByN() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] input = br.readLine().split(" ");

            int number = Integer.parseInt(input[0]);
            int n = Integer.parseInt(input[1]);
            List<Integer> divisor = new ArrayList<Integer>();

            for (int i = 1; i <= number; i++) {
                if (number % i == 0) {
                    divisor.add(i);
                }
            }

            if (n <= divisor.size()) System.out.println(divisor.get(n - 1));
            else System.out.println(0);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 약수들의 합
     * <a href="https://www.acmicpc.net/problem/9506">...</a>
     */
    public void sumDivisors() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = 0;
            while (true) {
                n = Integer.parseInt(br.readLine());
                if (n == -1) break;

                List<Integer> divisors = new ArrayList<Integer>();
                for (int i = 1; i < n; i++) {
                    if (n % i == 0) divisors.add(i);

                }

                int sum = divisors.stream().mapToInt(e -> e).sum();
                if (sum != n) {
                    System.out.println(String.format("%d is NOT perfect.", n));
                    continue;
                }

                System.out.printf("%d = %s\n", sum, divisors.stream().map(String::valueOf).collect(Collectors.joining(" + ")));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 소수 찾기
     * <a href="https://www.acmicpc.net/problem/1978">...</a>
     */
    public void findPrimeNumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int loop = Integer.parseInt(br.readLine());
            int cnt = 0;
            String[] n = br.readLine().split(" ");

            for (String s : n) {
                int number = Integer.parseInt(s);

                int f = 0;
                for (int k = 1; k < number; k++) {
                    if (number % k == 0) f++;
                }

                if (f == 1) cnt++;
            }

            System.out.println(cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
