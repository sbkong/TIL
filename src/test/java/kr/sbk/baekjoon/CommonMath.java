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
    public void honeyBeeHouse2 () {
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
}
