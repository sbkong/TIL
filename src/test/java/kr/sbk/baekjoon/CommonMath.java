package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The type Common math.
 */
public class CommonMath {

    /**
     * Convert base.
     * description https://www.acmicpc.net/problem/2745
     *
     * @throws Exception the exception
     */
    public void convertBase() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        char[] number = input[0].toCharArray();
        int numeralSystem = Integer.parseInt(input[1]);

        long[][] numberAndPosition = new long[number.length][2];
        for (int i = 0; i < number.length; i++) {
            if (number[i] >= 'A' && number[i] <= 'Z') {
                numberAndPosition[i][0] = number[i] - 55; // number
            } else if (number[i] >= '0' && number[i] <= '9') {
                numberAndPosition[i][0] = number[i] - 48;
            }

            numberAndPosition[i][1] = getDigitNumeral(number.length - i - 1, numeralSystem); // position
        }

        long sum = 0;
        for (int i = 0; i < numberAndPosition.length; i++) {
            sum += numberAndPosition[i][0] * numberAndPosition[i][1];
        }

        System.out.println(sum);
    }

    private long getDigitNumeral(int digit, int numeralSystem) {
        long sum = 1;
        for (int i = 0; i < digit; i++) {
            sum = sum * numeralSystem;
        }
        return sum;
    }

    /**
     * Convert base n.
     * description https://www.acmicpc.net/problem/11005
     *
     * @throws Exception the exception
     */
    public void convertBaseN() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int base = Integer.parseInt(input[1]);
        long numbers = Long.parseLong(input[0]);

        StringBuilder convertNumber = new StringBuilder();

        while (numbers >= base) {
            long remainder = numbers % base;
            if (remainder > 9) convertNumber.append((char) (remainder + 55));
            else convertNumber.append(remainder);

            numbers = numbers / base;
        }
        long remainder = numbers % base;
        if (remainder > 9) convertNumber.append((char) (remainder + 55));
        else convertNumber.append(remainder);

        System.out.println(convertNumber.reverse());

    }

    /**
     * Calc changes.
     * 잔돈 계산기
     */
    public void calcChanges() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int cases = Integer.parseInt(br.readLine().trim());

            int[] unit = {25, 10, 5, 1};
            while (cases-- > 0) {
                int money = Integer.parseInt(br.readLine().trim());

                for (int i = 0; i < unit.length; i++) {
                    System.out.print((money / unit[i]) + " ");
                    money = money % unit[i];
                }
                System.out.println();
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }


    /**
     * Calc add points of rectangle.
     * description https://www.acmicpc.net/problem/2903
     */
    public void calcAddPointsOfRectangle() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int level = Integer.parseInt(br.readLine().trim());

            int pointCount = calcSquare(2, level) + 1;
            pointCount = calcSquare(pointCount, 2);

            System.out.println(pointCount);

        } catch (IOException | NumberFormatException e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }

    private int calcSquare(int n, int times) {
        int result = 1;
        for (int i = 1; i <= times; i++) {
            result *= n;
        }
        return result;
    }

    /**
     * Calc add points of rectangle 2.
     * description Math.pow를 활용해서 더 간단하게 할 수 있다.
     */
    public void calcAddPointsOfRectangle2() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int level = Integer.parseInt(br.readLine().trim());

            int pointCount = (int) Math.pow(Math.pow(2, level) + 1, 2);

            System.out.println(pointCount);

        } catch (IOException | NumberFormatException e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }

    /**
     * Hoeny bee house.
     * https://www.acmicpc.net/problem/2292
     */
    public void hoenyBeeHouse() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int target = Integer.parseInt(br.readLine());

            if (target == 1) {
                System.out.println(1);
                return;
            }
            int octagonRangeBySteps = 1;
            int step = 1;
            for (int i = 1; ; i++) {
                octagonRangeBySteps += 6 * i;
                if (octagonRangeBySteps >= target) {
                    step = step + i;
                    break;
                }
            }
            System.out.println(step);

        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Honey bee house 2.
     */
    public void honeyBeeHouse2() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int target = Integer.parseInt(br.readLine());

            if (target == 1) {
                System.out.println(1);
                return;
            }

            int octagonRangeBySteps = 1;
            int step = 1;

            while (octagonRangeBySteps < target) {
                octagonRangeBySteps += 6 * step;
                step++;
            }

            System.out.println(step);

        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Search fractal.
     */
    public void searchFractal() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            // 입력을 받아서 target 변수에 저장
            int target = Integer.parseInt(br.readLine());

            // level, 방향 및 초기 좌표 설정
            int level = 1;
            int courseX = 1;
            int courseY = -1;
            int x = 1, y = 1;
            int cnt = 0;

            while (true) {
                // 방향을 반대로 바꿈
                courseX = -courseX;
                courseY = -courseY;

                // 방향에 따라 x, y 초기화
                if (courseX == 1) {
                    x = 1;
                    y = level;
                } else {
                    x = level;
                    y = 1;
                }

                // 내부 루프: 대각선으로 이동하며 좌표를 출력
                while (true) {
                    cnt++;
                    if (cnt == target) {
                        // 목표 숫자에 도달하면 해당 분수를 출력하고 종료
                        System.out.println(x + "/" + y);
                        return;
                    }

                    // 현재 좌표 출력
                    if (courseX == 1) {
                        x++;
                        y--;
                        if (y == 0) break;
                    } else {
                        x--;
                        y++;
                        if (x == 0) break;
                    }
                }
                // 다음 레벨로 증가
                level++;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Search fractal 2.
     * <p>
     * 입력 처리: 사용자가 입력한 숫자를 target 변수에 저장합니다.
     * 레벨 및 카운트 초기화: level과 cnt를 초기화합니다.
     * 목표 레벨 찾기: cnt + level이 target보다 작거나 같을 때까지 cnt에 level을 더하고 level을 증가시킵니다.
     * 오프셋 계산: target과 cnt의 차이를 offset에 저장합니다.
     * 분수 계산: level이 짝수인지 홀수인지에 따라 x와 y를 계산합니다.
     * 결과 출력: 계산된 x와 y를 분수 형태로 출력합니다.
     */
    public void searchFractal2() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int target = Integer.parseInt(br.readLine());

            int level = 1;
            int cnt = 1;

            while (cnt + level <= target) {
                cnt += level;
                level++;
            }

            int offset = target - cnt;
            int x, y;

            if (level % 2 == 0) {
                x = 1 + offset;
                y = level - offset;
            } else {
                x = level - offset;
                y = 1 + offset;
            }

            System.out.println(x + "/" + y);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
