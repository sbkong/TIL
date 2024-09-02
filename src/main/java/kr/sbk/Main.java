package kr.sbk;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        timeComplexity5();
    }

    public static void timeComplexity5() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            long n = Long.parseLong(br.readLine());

            if (n < 3) System.out.println(0);
            else System.out.println((long) n * (n - 1) * (n - 2) / 6);

            System.out.println(3);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
