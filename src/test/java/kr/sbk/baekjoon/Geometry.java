package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * The type Geometry.
 */
public class Geometry {
    /**
     * 사각형 너비 계산기
     */
    public void calcSquareMeter() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());

            System.out.println(n * m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 좌표 상에서 꼭지점으로 가는 최단 거리 계산.
     */
    static public void pathToPoint() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] input = br.readLine().split(" ");

            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);
            int h = Integer.parseInt(input[3]);

            int distanceX = w / 2 < x ? w - x : x;
            int distanceY = h / 2 < y ? h - y : y;

            System.out.println(Math.min(distanceX, distanceY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Where is the last point.
     */
    public void whereIsTheLastPoint() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {

            Scanner sc = new Scanner(System.in);

            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int x3 = sc.nextInt();
            int y3 = sc.nextInt();

            int x4, y4;

            if (x1 == x2) {
                x4 = x3;
            } else if (x1 == x3) {
                x4 = x2;
            } else {
                x4 = x1;
            }

            if (y1 == y2) {
                y4 = y3;
            } else if (y1 == y3) {
                y4 = y2;
            } else {
                y4 = y1;
            }

            System.out.println(x4 + " " + y4);

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
