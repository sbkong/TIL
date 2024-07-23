package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TwoDimensionMatrix {


    public void plusMatrix() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int column = Integer.parseInt(input[0]);
        int row = Integer.parseInt(input[1]);

        List<Integer>[][] firstMatrix = new ArrayList[row][column];
        List<Integer>[][] secondMatrix = new ArrayList[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                firstMatrix[i][j] = new ArrayList<>();
                secondMatrix[i][j] = new ArrayList<>();
            }
        }

        // input value into matrix
        for (int i = 0; i < row; i++) {
            String[] rowInput = br.readLine().split(" ");

            for (int j = 0; j < column; j++) {
                firstMatrix[i][j].add(Integer.parseInt(rowInput[j]));
            }
        }

        for (int i = 0; i < row; i++) {
            String[] rowInput = br.readLine().split(" ");

            for (int j = 0; j < column; j++) {
                secondMatrix[i][j].add(Integer.parseInt(rowInput[j]));
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int sum = firstMatrix[i][j].get(0) + secondMatrix[i][j].get(0);
                System.out.print(sum + " ");
            }
            System.out.println();
        }
    }

    public void getMaxAndPosition() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] twoDimensionMatrix = new int[9][9];

        int max = Integer.MIN_VALUE;
        int[] position = new int[2];

        for (int i = 0; i < 9; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                twoDimensionMatrix[i][j] = Integer.parseInt(input[j]);

                if (twoDimensionMatrix[i][j] > max) {
                    max = twoDimensionMatrix[i][j];
                    position[0] = i + 1;
                    position[1] = j + 1;
                }
            }

        }

        System.out.println(max);
        System.out.println(position[0] + " " + position[1]);
    }
}
