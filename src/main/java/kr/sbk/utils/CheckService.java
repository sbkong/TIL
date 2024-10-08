package kr.sbk.utils;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CheckService {

    public static void verification() {
        int[] numbers = new int[1000000];
        List<Integer> list = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(1000000) + 1;
            list.add(numbers[i]);
        }

        calcSpentTime(() -> Arrays.parallelSort(numbers));
        calcSpentTime(() -> list.sort(Integer::compareTo));
        calcSpentTime(() -> Collections.sort(list));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        calcSpentTime(() -> Arrays.stream(numbers).forEach(e -> {
            sb.append(e).append("\n");
        }));

    }

    public static void calcSpentTime(Runnable task) {
        // 메서드 수행 시간 측정을 위한 시간 기록
        long startTime = System.nanoTime();

        task.run();

        long endTime = System.nanoTime();
        // 수행 시간 출력 (밀리초로 변환)
        System.out.println("Execution time: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    public static void calcSpentMemory(Runnable task) {
        // 메모리 사용량 측정을 위한 Runtime 인스턴스 생성
        Runtime runtime = Runtime.getRuntime();
        // 실행 전 메모리 사용량 측정
        long beforeUsedMem = runtime.totalMemory() - runtime.freeMemory();

        task.run();

        // 실행 후 메모리 사용량 측정
        long afterUsedMem = runtime.totalMemory() - runtime.freeMemory();
        // 메모리 사용량 출력
        System.out.println("Memory used: " + (afterUsedMem - beforeUsedMem) + " bytes");
    }
}
