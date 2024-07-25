package kr.sbk.baekjoon;

import java.io.BufferedReader;
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
}
