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

    /**
     * <a href="https://www.acmicpc.net/problem/14425">...</a>
     */
    public static void containsString() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = br.readLine().split(" ");
            int referenceCount = Integer.parseInt(input[0]);
            int differenceCount = Integer.parseInt(input[1]);

            Set<String> referenceSet = new HashSet<>();
//            Map<String, Integer> differenceMap = new HashMap<>();

            for (int i = 0; i < referenceCount; i++) {
                referenceSet.add(br.readLine());
            }
//            for (int i = 0; i < differenceCount; i++) {
//                differenceMap.put(br.readLine(), 1);
//            }

            /* 람다식 적용 */
//            Set<String> referenceSet = IntStream.range(0, referenceCount)
//                .mapToObj(i -> br.readLine())
//                .collect(Collectors.toSet());
//
//            Map<String, Integer> differenceMap = IntStream.range(0, differenceCount)
//                .mapToObj(i -> {
//                    try {
//                        return br.readLine();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//                .collect(Collectors.toMap(key -> key, value -> 1));
//

            int matchCount = 0;
            for (int i = 0; i < differenceCount; i++) {
                String word = br.readLine();
                if (referenceSet.contains(word)) {
                    matchCount++;
                }
            }

            System.out.println(matchCount);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
