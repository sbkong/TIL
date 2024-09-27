package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
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
     * @throws IOException the io exception
     */
    public void simultaneousEquation() throws IOException {
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


    /**
     * Gets refill cell count.
     * <a href="https://www.acmicpc.net/problem/1018">...</a>
     */
    public static void getRefillCellCount() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {


            // N: 세로 크기, M: 가로 크기
            String[] size = br.readLine().split(" ");
            int N = Integer.parseInt(size[0]);
            int M = Integer.parseInt(size[1]);

            // 보드 입력 받기
            char[][] board = new char[N][M];
            for (int i = 0; i < N; i++) {
                board[i] = br.readLine().toCharArray();
            }

            int minRepaint = Integer.MAX_VALUE;

            // 가능한 8x8 체스판을 잘라서 확인
            for (int i = 0; i <= N - 8; i++) {
                for (int j = 0; j <= M - 8; j++) {
                    minRepaint = Math.min(minRepaint, countRepaints(board, i, j));
                }
            }

            System.out.println(minRepaint);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static int countRepaints(char[][] board, int x, int y) {
        int count1 = 0; // 왼쪽 위가 'W'인 경우
        int count2 = 0; // 왼쪽 위가 'B'인 경우

        // 8x8 체스판 순회
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 현재 칸의 색깔 예상 (패턴1: 왼쪽 위 W, 패턴2: 왼쪽 위 B)
                char expectedColor1 = (i + j) % 2 == 0 ? 'W' : 'B';
                char expectedColor2 = (i + j) % 2 == 0 ? 'B' : 'W';

                if (board[x + i][y + j] != expectedColor1) {
                    count1++;
                }
                if (board[x + i][y + j] != expectedColor2) {
                    count2++;
                }
            }
        }

        return Math.min(count1, count2); // 두 패턴 중 더 적은 칠하기 횟수 반환
    }


    /**
     * <a href="https://www.acmicpc.net/problem/1436">...</a>
     *
     * 아래 주석된 코드와 같이 문자열을 잘라서 계산하면 메모리를 많이 사용한다.
     *
     * series=10000 일 때,
     * String.split() 사용 : 320MB
     * int 사용 : 0 byte
     */
    public void satanSeries() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int series = Integer.parseInt(br.readLine());

            int seriesNumber = 0;
            int seriesCount = 0;
            for (int i = 666; i < Integer.MAX_VALUE; i++) {
                if (seriesCount == series) {
                    break;
                }
                int temp = i;
                int count = 0;

                while (temp > 0) {
                    if (temp % 10 == 6) {
                        count++;
                    } else {
                        count = 0;
                    }
                    if (count == 3) {
                        seriesCount++;
                        seriesNumber = i;
                        break;
                    }
                    temp /= 10;
                }

                /*String[] separated = String.valueOf(i).split("");
                for (int j = 0; j < separated.length; j++) {
                    int n = Integer.parseInt(separated[j]);
                    if (n == 6 && j + 1 < separated.length && j + 2 < separated.length) {
                        int m = Integer.parseInt(separated[j + 1]);
                        int o = Integer.parseInt(separated[j + 2]);

                        if (m == 6 && o == 6) {
                            seriesNumber = i;
                            seriesCount++;
                            break;
                        }
                    }
                }*/
            }
            System.out.println(seriesNumber);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://www.acmicpc.net/problem/2839">...</a>
     */
    public static void delivery() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int weight = Integer.parseInt(br.readLine());

            int five = 5;
            int three = 3;

            int min = Integer.MAX_VALUE;

            for (int i = 0; i <= weight / five; i++) {
                int remainder = weight - (five * i);

                if (remainder % three == 0) {
                    min = Math.min(min, remainder / three + i);
                }
            }

            System.out.println(min == Integer.MAX_VALUE ? -1 : min);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
