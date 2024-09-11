package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Brute force.
 */
public class BruteForce {

    /**
     * Black jack.
     * <a href="https://www.acmicpc.net/problem/2798">...</a>
     * <p>
     * 시간 복잡도는 O(n^3) : 대략 최대 100만 번 연산, 3Ghz 컴퓨터로 0.0033초
     * 물론 실제로는 여러가지 이유로 이것보다 오래 걸리겠지
     */
    public static void blackJack() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String[] input = br.readLine().split(" ");

            int countCard = input[0].length();
            int targetNumber = Integer.parseInt(input[1]);
            int nearTargetNumber = 0;

            List<Integer> cards = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

            int maxSum = 0;

            for (int i = 0; i < cards.size() - 2; i++) {
                for (int j = i + 1; j < cards.size() - 1; j++) {
                    for (int k = j + 1; k < cards.size(); k++) {
                        int sum = cards.get(i) + cards.get(j) + cards.get(k);
                        if (sum <= targetNumber && sum > maxSum) {
                            maxSum = sum;
                        }
                    }
                }
            }

            System.out.println(maxSum);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 분해 합.
     * <a href="https://www.acmicpc.net/problem/2231">...</a>
     */
    public void disassembledSum() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String n = br.readLine();

            int disassembledSum = Integer.parseInt(n);

            for (int i = 0; i < disassembledSum; i++) {
                if (disassembledSum == i + Arrays.stream(String.valueOf(i).split("")).mapToInt(Integer::parseInt).sum()) {
                    System.out.println(i);
                    break;
                } else if (i == disassembledSum - 1) {
                    System.out.println(0);
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * 연립방정식 해 구하기
    * https://www.acmicpc.net/problem/19532
    */
    public void simultaneousEquation(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        int c = Integer.parseInt(input[2]);
        int d = Integer.parseInt(input[3]);
        int e = Integer.parseInt(input[4]);
        int f = Integer.parseInt(input[5]);

        for (int x = -999; x <= 999; x++) {
            for (int y = -999; y <= 999; y++) {
                if ((a * x) + (b * y) == c && (d * x) + (e * y) == f) {
                    System.out.printf("%d %d%n", x, y);
                }
            }
        }
    }
}
