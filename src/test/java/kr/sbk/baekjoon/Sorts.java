package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Sorts.
 */
public class Sorts {


    /**
     * <a href="https://www.acmicpc.net/problem/2750">...</a>
     */
    public static void sortSmallToLarge() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int numberCount = Integer.parseInt(br.readLine());

            List<Integer> numbers = new ArrayList<>();
            while (numberCount > 0) {
                numbers.add(Integer.parseInt(br.readLine()));
                numberCount--;
            }
            numbers.sort(Integer::compareTo);

            numbers.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 버블 정렬
     */
    public static void sortSmallToLarge2() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int numberCount = Integer.parseInt(br.readLine());

            List<Integer> numbers = new ArrayList<>();
            while (numberCount > 0) {
                numbers.add(Integer.parseInt(br.readLine()));
                numberCount--;
            }

            for (int i = 0; i < numbers.size() - 1; i++) {
                for (int j = i + 1; j < numbers.size(); j++) {
                    if (numbers.get(i) > numbers.get(j)) {
                        int temp = numbers.get(i);
                        numbers.set(i, numbers.get(j));
                        numbers.set(j, temp);
                    }
                }
            }

            numbers.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://www.acmicpc.net/problem/2587">...</a>
     */
    public static void averageAndMedian() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int input = 5;
            int numberCount = input;
            List<Integer> numbers = new ArrayList<>();
            while (numberCount > 0) {
                numbers.add(Integer.parseInt(br.readLine()));
                numberCount--;
            }
            numbers.sort(Integer::compareTo);

            numbers.stream().mapToInt(sum -> sum).average()
                .ifPresent(e -> System.out.println((int) e));

            int medianIndex = (int) Math.ceil(input / 2.0);

            System.out.println(numbers.get(medianIndex - 1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://www.acmicpc.net/problem/25305">...</a>
     */
    public static void getWinnerCutLine() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = br.readLine().split(" ");

            int examinees = Integer.parseInt(input[0]);
            int winnerCount = Integer.parseInt(input[1]);

            List<Integer> scores = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

            scores.sort((o1, o2) -> o2 - o1);

            System.out.println(scores.get(winnerCount - 1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
