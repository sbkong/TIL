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
     *
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
}
