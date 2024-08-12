package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
}
