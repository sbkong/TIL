package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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


    /**
     * <a href="https://www.acmicpc.net/problem/7785">...</a>
     *
     *
     */
    public static void remainderPerson() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int logCount = Integer.parseInt(br.readLine());

            Map<String, String> entryState = new TreeMap<String, String>(Comparator.reverseOrder());

            for (int i = 0; i < logCount; i++) {
                String[] inputs = br.readLine().split(" ");

                entryState.remove(inputs[0]);
                entryState.put(inputs[0], inputs[1]);
            }

            entryState.forEach((member, state) -> {
                if ("enter".equals(state)) {
                    System.out.println(member);
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * <a href="https://www.acmicpc.net/problem/1620">...</a>
     */
    public static void findPokemon() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] logCount = br.readLine().split(" ");
            int listCount = Integer.parseInt(logCount[0]);
            int questionCount = Integer.parseInt(logCount[1]);

            Map<Integer, String> pokemonList = new HashMap<>();
            Map<String, Integer> pokemonIndex = new HashMap<>();

            for (int i = 0; i < listCount; i++) {
                String pokemonName = br.readLine();
                pokemonList.put(i, pokemonName);
                pokemonIndex.put(pokemonName, i);
            }

            for (int i = 0; i < questionCount; i++) {
                String question = br.readLine();

                if (question.matches("-?\\d+")) {
                    int index = Integer.parseInt(question);
                    System.out.println(pokemonList.get(index - 1));
                } else {
                    System.out.println(pokemonIndex.get(question) + 1);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://www.acmicpc.net/problem/10816">...</a>
     */
//시간 초과
    public static void findGetCardCount() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(br.readLine());

            int[] haveCards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();

            int m = Integer.parseInt(br.readLine());

            int[] findCards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();

            for (int i = 0; i < findCards.length; i++) {
                int finalI = i;
                System.out.print(
                    Arrays.stream(haveCards).filter(c -> c == findCards[finalI]).count() + " ");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 입력하면서 카드 번호에 대한 개수를 세고 Map에 넣어놓음.
     */
    public static void findGetCardCount2() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(br.readLine());

            int[] haveCards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();

            int m = Integer.parseInt(br.readLine());

            int[] findCards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();

            // Use a HashMap to store the count of each card in `haveCards`
            Map<Integer, Integer> cardCountMap = new HashMap<>();
            for (int card : haveCards) {
                cardCountMap.put(card, cardCountMap.getOrDefault(card, 0) + 1);
            }

            // For each card in `findCards`, retrieve the count from the HashMap
            StringBuilder result = new StringBuilder();
            for (int card : findCards) {
                result.append(cardCountMap.getOrDefault(card, 0)).append(" ");
            }

            // Print all counts at once
            System.out.println(result.toString().trim());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://www.acmicpc.net/problem/1764">...</a>
     */
    public static void findPerson() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            Set<String> nameNeverHaveHeard = new TreeSet<>();
            Set<String> nameNeverHaveSaw = new TreeSet<>();

            for (int i = 0; i < n; i++) {
                String name = br.readLine();
                nameNeverHaveHeard.add(name);
            }
            for (int i = 0; i < m; i++) {
                String name = br.readLine();
                nameNeverHaveSaw.add(name);
            }

            AtomicInteger count = new AtomicInteger();
            StringBuilder result = new StringBuilder();

            nameNeverHaveSaw.forEach(name -> {
                if (nameNeverHaveHeard.contains(name)) {
                    count.getAndIncrement();
                    result.append(name).append("\n");
                }
            });

            System.out.println(count + "\n" + result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://www.acmicpc.net/problem/1269">...</a>
     */
    public static void groupCount() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String[] input = br.readLine().split(" ");

            Set<Integer> groupA = new TreeSet<>();
            Set<Integer> groupB = new TreeSet<>();

            Arrays.stream(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()).mapToObj(
                groupA::add).collect(Collectors.toList());

            Arrays.stream(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()).mapToObj(
                groupB::add).collect(Collectors.toList());

            AtomicInteger countA = new AtomicInteger();
            AtomicInteger countB = new AtomicInteger();

            groupB.forEach(name -> {
                if (groupA.contains(name)) {
                    countA.getAndIncrement();
                }
            });

            groupA.forEach(name -> {
                if (groupB.contains(name)) {
                    countB.getAndIncrement();
                }
            });

            System.out.println( (groupA.size() - countA.get() ) + (groupB.size() - countB.get()) );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://www.acmicpc.net/problem/11478">...</a>
     */
    public static void getDifferentWordCount() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String input = br.readLine();
            int len = input.length() + 1;

            Set<String> possibleWords = new HashSet<>();

//            for (int i = 1; i < len; i++) {
//                for (int j = 0; j <= i; j++) {
//                    String word = input.substring(j, i);
//                    possibleWords.add(word);
//                }
//            }
//            possibleWords.remove("");

            for (int i = 0; i < input.length(); i++) {
                for (int j = i + 1; j <= input.length(); j++) {
                    possibleWords.add(input.substring(i, j));
                }
            }

            System.out.println(possibleWords.size());
//            possibleWords.forEach(System.out::println);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
