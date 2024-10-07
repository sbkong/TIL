package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

}
