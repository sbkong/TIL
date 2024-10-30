package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Set and map.
 */
public class SetAndMap {

    /**
     * <a href="https://www.acmicpc.net/problem/10815">...</a>
     */
    public static void containsCard() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int ownCardCount = Integer.parseInt(br.readLine());

            Set<Integer> ownCard = new HashSet<>();
            Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .forEach(ownCard::add);

//            ownCard.forEach(c -> System.out.printf("%d ", c));

            int checkCardCount = Integer.parseInt(br.readLine());
            int[] checkCards = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

            Arrays.stream(checkCards).forEach(i -> {
                if (ownCard.contains(i)) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
