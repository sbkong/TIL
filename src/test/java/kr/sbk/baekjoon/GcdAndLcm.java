package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Gcd and lcm.
 */
public class GcdAndLcm {

    /**
     * Find gcm.
     */
    public static void findGcm() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int input = Integer.parseInt(br.readLine());

            for (int i = 0; i < input; i++) {
                int[] numbers = new int[2];
                String[] in = br.readLine().split(" ");
                numbers[0] = Integer.parseInt(in[0]);
                numbers[1] = Integer.parseInt(in[1]);

                Set<Integer> s0 = new TreeSet<Integer>(Comparator.reverseOrder());
                Set<Integer> s1 = new TreeSet<Integer>(Comparator.reverseOrder());

                for (int j = 1; j <= numbers[0]; j++) {
                    if (numbers[0] % j == 0) {
                        s0.add(j);
                    }
                }

                for (int j = 1; j <= numbers[0]; j++) {
                    if (numbers[1] % j == 0) {
                        s1.add(j);
                    }
                }

                AtomicInteger lcm = new AtomicInteger();

                s1.stream()
                    .filter(s0::contains)
                    .findFirst()
                    .ifPresent(lcm::set);

                System.out.println((numbers[0] * numbers[1]) / lcm.get());

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Find lcd.
     */
    public static void findLcd() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong)
                .toArray();

            input = Arrays.stream(input).sorted().toArray();

            long n = input[0];
            long m = input[1];

            /* 최대 공약수 */
            while (n != 0) {
                long temp = n;
                n = m % n;
                m = temp;
            }

            /* 최소 공배수 */
            System.out.println((input[1] * input[0]) / m);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sum fraction.
     */
    public static void sumFraction() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int denominator = a[1] * b[1];
            int numerator = (b[1] * a[0]) + (a[1] * b[0]);

            int n = numerator;
            int m = denominator;

            while (n != 0) {
                int temp = n;
                n = m % n;
                m = temp;
            }

            System.out.println((numerator / m) + " " + (denominator / m));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Planting trees.
     * 핵심은 입력 크기가 최대 100,000이며, 각 가로수 위치는* 10^9 까지 주어질 수 있기 때문에 O(n \log \text{maxGap}) 복잡도 내에서 문제를 해결해야 한다는 점
     *
     * 최대 공약수 계산:
     * 각 간격에 대해 한 번씩만 GCD를 계산합니다. 이 과정은
     * 𝑂(𝑛log maxGap)
     *
     * 추가 나무 계산:
     * 첫 번째와 마지막 위치 사이에서 gcd 간격으로 가로수를 배치했을 때 필요한 개수를 O(1)로 계산
     *
     * 1. **간격 계산과 GCD**:
     *    - 모든 간격을 순회하며 GCD를 계산. \(O(n \log \text{maxGap})\).
     *
     * 2. **추가 나무 계산 최적화**:
     *    - 가로수의 총 간격을 \(gcd\)로 나눈 값에서 이미 심어진 가로수의 간격을 제외. 이 과정은 \(O(1)\)입니다.
     *
     * ### 시간 복잡도
     * 1. GCD 계산: \(O(n \log \text{maxGap})\).
     * 2. 필요한 나무 계산: \(O(1)\).
     * 3. 입력 처리: \(O(n)\).
     *
     * ### 공간 복잡도
     * - 배열 크기는 \(O(n)\), 추가 메모리는 상수 수준.
     */
    public static void plantingTrees() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(br.readLine());

            /*List<Integer> plantingPoints = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                plantingPoints.add(Integer.parseInt(br.readLine()));
            }

            int gcd = plantingPoints.get(1) - plantingPoints.get(0);
            for (int i = 2; i < n; i++) {
                gcd = getGCD(gcd, plantingPoints.get(i) - plantingPoints.get(i - 1));
            }

            int needPlantingTreeCount = 0;
            int first = plantingPoints.get(0);
            int last = plantingPoints.get(n - 1);

            for (int i = first + gcd; i < last; i += gcd) {
                if (!plantingPoints.contains(i)) {
                    needPlantingTreeCount++;
                }
            }*/

//

            int[] plantingPoints = new int[n];

            for (int i = 0; i < n; i++) {
                plantingPoints[i] = Integer.parseInt(br.readLine());
            }

            int gcd = plantingPoints[1] - plantingPoints[0];
            for (int i = 2; i < n; i++) {
                gcd = getGCD(gcd, plantingPoints[i] - plantingPoints[i - 1]);
            }

            int totalGaps = (plantingPoints[n - 1] - plantingPoints[0]) / gcd;
            int needPlantingTreeCount = totalGaps - (n - 1);

//
            System.out.println(needPlantingTreeCount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 최대공약수 계산 함수 (유클리드 알고리즘)
    private static int getGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


    /**
     * Find prime number.
     */
    public static void findPrimeNumber() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                long number = Long.parseLong(br.readLine());

                // 입력 숫자와 같거나 큰 첫 번째 소수 찾기
                long primeNumber = findNextPrime(number);

                System.out.println(primeNumber);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isPrime(long num) {
        if (num < 2) {
            return false;
        }
        for (long i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static long findNextPrime(long number) {
        while (true) {
            if (isPrime(number)) {
                return number;
            }
            number++;
        }
    }
}
