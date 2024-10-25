package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
     * @param <ex>       the type parameter
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

    /**
     * Sort o log n 2.
     */
    public static void sortOLogN2() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(br.readLine());

            List<int[]> points = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String[] input = br.readLine().split(" ");
                points.add(new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])});
            }

            points.sort((a, b) -> {
                if (b[1] != a[1]) {
                    return Integer.compare(a[1], b[1]);
                } else {
                    if (b[0] != a[0]) {
                        return Integer.compare(a[0], b[0]);
                    }
                }
                return 0;
            });

            points.forEach(p -> System.out.println(p[0] + " " + p[1]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Sort word.
     */
    public static void sortWord() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(br.readLine());

            Set<String> inputWords = new HashSet<>();
            for (int i = 0; i < count; i++) {
                String input = br.readLine();
                if (!isNumeric(input)) {
                    inputWords.add(input);
                }
            }

            List<String> distinctWords = new ArrayList<>(inputWords);

            distinctWords.sort((o1, o2) -> {
                return o1.compareTo(o2);
            });

            distinctWords.sort((o1, o2) -> {
                return o1.length() - o2.length();
            });

            distinctWords.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://www.acmicpc.net/problem/1181">...</a>
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * <a href="https://www.acmicpc.net/problem/10814">...</a>
     */
    public static void sortAgeAndName() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(br.readLine());

            List<Map<Integer, String>> users = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String[] input = br.readLine().split(" ");

                /* 전통적인 방법 */
//                Map<Integer, String> user = new HashMap<>();
//                user.put(Integer.parseInt(input[0]), input[1]);
//                users.add(user);

                /* 한 줄로 삽입 */
                users.add(Collections.singletonMap(Integer.parseInt(input[0]), input[1]));
            }

            /* users의 input[0]끼리 비교 정렬, 값이 같으면 그냥 유지*/
            users.sort((u1, u2) -> {
                Integer age1 = u1.keySet().iterator().next();
                Integer age2 = u2.keySet().iterator().next();

                /* 이름 정렬도 필요할 경우*/
//                String name1 = u1.values().iterator().next();
//                String name2 = u2.values().iterator().next();

                int ageComparison = age1.compareTo(age2);
//                if (ageComparison != 0) {
                return ageComparison;
//                }
//                return name1.compareTo(name2);
//                return 0;
            });

            users.forEach(user -> {
                Integer age = user.keySet().iterator().next();
                String name = user.values().iterator().next();
                System.out.println(age + " " + name);
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * The type User.
     */
    static class User {

        /**
         * The Age.
         */
        int age;
        /**
         * The Name.
         */
        String name;

        /**
         * Instantiates a new User.
         *
         * @param age the age
         * @param name the name
         */
        User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return age + " " + name;
        }
    }

    /**
     * Sort age and name 2.
     */
    public static void sortAgeAndName2() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(br.readLine());

            List<User> users = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String[] input = br.readLine().split(" ");
                users.add(new User(Integer.parseInt(input[0]), input[1]));
            }

            /* 나이로 정렬, 나이가 같으면 이름으로 정렬 */
            users.sort(Comparator.comparingInt((User u) -> u.age).thenComparing(u -> u.name));

            users.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sort age and name 3.
     */
    public static void sortAgeAndName3() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(br.readLine());

            List<String[]> users = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String[] input = br.readLine().split(" ");
                users.add(input);
            }

            /* 나이로 정렬, 나이가 같으면 이름으로 정렬 */
            users.sort((u1, u2) -> {
                int ageComparison = Integer.parseInt(u1[0]) - Integer.parseInt(u2[0]);
                if (ageComparison != 0) {
                    return ageComparison;
                }
                return u1[1].compareTo(u2[1]);
            });

            users.forEach(user -> System.out.println(user[0] + " " + user[1]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
