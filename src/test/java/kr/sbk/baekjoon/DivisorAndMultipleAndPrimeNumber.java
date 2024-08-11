package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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

    /**
     * Prime number in range.
     * <a href="https://www.acmicpc.net/problem/2581">...</a>
     *
     * @description 2부터 X-1까지 모두 나눠서 X가 소수인지 판별하는 문제 2
     */
    public void primeNumberInRange() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int m = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            List<Integer> divisors = new ArrayList<Integer>();

            for (int i = m; i <= n; i++) {

                int cnt = 0;
                for (int j = 2; j <= i; j++) {
                    if (i % j == 0) {
                        cnt++;
                    }
                }
                if (cnt < 2 && i != 1) divisors.add(i);
            }
            if (divisors.isEmpty()) {
                System.out.println(-1);
                return;
            }
            System.out.println(divisors.stream().mapToInt(e -> e).sum());
            System.out.println(divisors.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prime number in range 2.
     * <p>
     * 에라토스테네스의 체 공식을 사용하면 쉽게 구할 수 있다.
     */
    public void primeNumberInRange2() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int M = Integer.parseInt(br.readLine());
            int N = Integer.parseInt(br.readLine());

            boolean[] isPrime = new boolean[N + 1];
            for (int i = 2; i <= N; i++) {
                isPrime[i] = true;
            }

            for (int i = 2; i * i <= N; i++) {
                if (isPrime[i]) {
                    for (int j = i * i; j <= N; j += i) {
                        isPrime[j] = false;
                    }
                }
            }

            List<Integer> primes = new ArrayList<>();
            for (int i = M; i <= N; i++) {
                if (isPrime[i]) {
                    primes.add(i);
                }
            }

            if (primes.isEmpty()) {
                System.out.println(-1);
            } else {
                int sum = primes.stream().mapToInt(Integer::intValue).sum();
                int min = primes.get(0);
                System.out.println(sum);
                System.out.println(min);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * N을 소인수분해하는 문제 with AtomicInteger
     */
    static void factorizationInPrimeFactors() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            AtomicInteger n = new AtomicInteger(Integer.parseInt(br.readLine()));

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
                            n /= v / e;
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * N을 소인수분해하는 문제
     */
    static void factorizationInPrimeFactors2() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(br.readLine());

            // 정수 n의 소수 찾기
            List<Integer> primeNumbers = new ArrayList<>();
            for (int i = 2; i <= n; i++) {
                if (n % i == 0) {
                    primeNumbers.add(i);
                }
            }

            for (int i = 0; i < primeNumbers.size(); i++) {
                while (n % primeNumbers.get(i) == 0) {
                    System.out.println(primeNumbers.get(i));
                    n /= primeNumbers.get(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
