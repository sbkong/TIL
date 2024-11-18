package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Gcd and lcm.
 */
public class GcdAndLcm {

    /**
     * Find gcm.
     */
    public static void findGcm() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int input = Integer.parseInt(br.readLine());

            for (int i = 0; i < input; i++) {
                int[] numbers = new int[2];
                String[] in = br.readLine().split(" ");
                numbers[0] = Integer.parseInt(in[0]);
                numbers[1] = Integer.parseInt(in[1]);

                Set<Integer> s0 = new TreeSet<Integer>(Comparator.reverseOrder());
                Set<Integer> s1 = new TreeSet<Integer>(Comparator.reverseOrder());

                for (int j = 1; j <= numbers[0]; j++) {
                    if (numbers[0] % j == 0) {
                        s0.add(j);
                    }
                }

                for (int j = 1; j <= numbers[0]; j++) {
                    if (numbers[1] % j == 0) {
                        s1.add(j);
                    }
                }

                AtomicInteger lcm = new AtomicInteger();

                s1.stream()
                    .filter(s0::contains)
                    .findFirst()
                    .ifPresent(lcm::set);

                System.out.println( ( numbers[0] * numbers[1]) / lcm.get() );

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
