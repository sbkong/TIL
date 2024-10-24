package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    /**
     * <a href="https://www.acmicpc.net/problem/2751">...</a>
     * 출력 부분의 시간 초과가 영향이 컸다.
     * 그리고 정렬은 Collections 클래스를 사용하면 빠르다.
     * O(n^2) 대신 O(log(n))을 사용하는 정렬 기법을 사용해야 한다.
     *
     * <p> 참고 </p>
     * <a href="https://www.acmicpc.net/board/view/149136">...</a>
     * <a href="https://www.acmicpc.net/board/view/31887">...</a>
     *<a href="https://codeforces.com/blog/entry/7108">...</a>
     *
     * @param <ex>   the type parameter
     */
    public static <ex> void sortSmallToLargeTwo() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> numbers = new ArrayList<>();
            int numberCount = Integer.parseInt(br.readLine());

            while (numberCount > 0) {
                numbers.add(Integer.parseInt(br.readLine()));
                numberCount--;
            }
//            numbers.sort(Integer::compareTo); // 178.7896 ms
            Collections.sort(numbers); // 7.0679 ms

//            numbers.forEach(System.out::println); // 4222 ms

            StringBuilder sb = new StringBuilder();

            numbers.forEach(e -> sb.append(e).append("\n")); // 19.2457 ms
//            System.out.println(sb); // 1368 ms

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                bw.write(sb.toString()); // 1328 ms
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://www.acmicpc.net/problem/2751">...</a>
     */
    public static void sortSmallToLargeTwo2() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int numberCount = Integer.parseInt(br.readLine());

            int[] numbers = new int[numberCount];

            for (int i = 0; i < numberCount; i++) {
                numbers[i] = Integer.parseInt(br.readLine());
            }
            Arrays.parallelSort(numbers); // 260.793 ms

            Arrays.stream(numbers).forEach(System.out::println); // 4222 ms
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://www.acmicpc.net/problem/1427">...</a>
     */
    public static void sortDesc() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> splitNumber = Arrays.stream(br.readLine().split(""))
                .map(Integer::parseInt).collect(Collectors.toList());

            splitNumber.sort((o1, o2) -> o2 - o1);

            splitNumber.forEach(System.out::print);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * O(logN) 정렬 알고리즘을 사용해야함
     * <a href="https://www.acmicpc.net/problem/11650">...</a>
     */
    public static void sortOLogN() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(br.readLine());

            List<int[]> points = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String[] input = br.readLine().split(" ");
                points.add(new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])});
            }

            points.sort((a, b) -> {
                if (b[0] != a[0]) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    if (b[1] != a[1]) {
                        return Integer.compare(a[1], b[1]);
                    }
                }
                return 0;
            });

            points.forEach(p -> System.out.println(p[0] + " " + p[1]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
