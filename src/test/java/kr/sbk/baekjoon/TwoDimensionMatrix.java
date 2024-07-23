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

    public void printMatrix() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[][] stringMatrix = new String[5][15];

        for (int i = 0; i < 5; i++) {
            char[] input = br.readLine().toCharArray();

            for (int j = 0; j < input.length; j++) {
                stringMatrix[i][j] = String.valueOf(input[j]);
            }
        }


        for (int j = 0; j < stringMatrix[0].length; j++) {
            for (int i = 0; i < stringMatrix.length; i++) {
                if (stringMatrix[i][j] != null && !"".equals(stringMatrix[i][j]))
                    System.out.print(stringMatrix[i][j]);
            }
        }
    }



    /*
    색종이 넓이 구하기
    https://www.acmicpc.net/problem/2563
     */

    // 사각형의 너비를 구하고 각 사각형의 크기를 빼려고 했다.
    // 중학교에서 배운 것 대로 하려고 했었는데 잘 안됐다.
    // 중복 넓이를 빼는 것이 어려웠다.
    public void calcArea() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] paper = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] position = br.readLine().split(" ");
            for (int j = 0; j < 2; j++) {
                paper[i][j] = Integer.parseInt(position[j]);
            }
        }

        int[] duplicateArea = new int[n];
        int[][] pairs = {{0, 1}, {0, 2}, {1, 2}};

        for (int i = 0; i < pairs.length; i++) {
            int x1 = paper[pairs[i][0]][0];
            int x2 = paper[pairs[i][1]][0];
            int y1 = paper[pairs[i][0]][1];
            int y2 = paper[pairs[i][1]][1];

            int biggerNumberX = Math.max(x1, x2) + 10;
            int smallerNumberX = Math.min(x1, x2) + 10;
            int biggerNumberY = Math.max(y1, y2) + 10;
            int smallerNumberY = Math.min(y1, y2) + 10;

            if (biggerNumberX - smallerNumberX < 10 && biggerNumberY - smallerNumberY < 10) {
                duplicateArea[i] = (10 - (biggerNumberX - smallerNumberX)) * (10 - (biggerNumberY - smallerNumberY));
            }
        }

        int sumArea = 100 * n;

        for (int i = 0; i < n; i++) {
            sumArea -= duplicateArea[i];
        }

        System.out.println(sumArea);
    }

    // 흰 도화지를 기준으로 사각형의 공간을 1로 바꿨다.
    // 1로 바꾼 행렬의 값을 모두 더하면 곧 넓이가 된다.
    public void calcArea2() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] paper = new int[100][100];
        int n = Integer.parseInt(br.readLine());

        for (int k = 0; k < n; k++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            for (int i = x; i < x + 10; i++) {
                for (int j = y; j < y + 10; j++) {
                    paper[i][j] = 1;
                }
            }
        }

        int area = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (paper[i][j] == 1) {
                    area++;
                }
            }
        }

        System.out.println(area);
    }
}
