package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    /**
     * Where is the last point 2.
     */
    public void whereIsTheLastPoint2() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            List<Integer> pointsX = new ArrayList<>();
            List<Integer> pointsY = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                String[] givenPoint = br.readLine().split(" ");
                pointsX.add(Integer.parseInt(givenPoint[0]));
                pointsY.add(Integer.parseInt(givenPoint[1]));
            }

            int remainderPointX = pointsX.get(0);
            int remainderPointY = pointsY.get(0);

            if(remainderPointX == pointsX.get(1))
                remainderPointX = pointsX.get(2);
            else if(remainderPointX == pointsX.get(2))
                remainderPointX = pointsX.get(1);

            if(remainderPointY == pointsY.get(1))
                remainderPointY = pointsY.get(2);
            else if(remainderPointY == pointsY.get(2))
                remainderPointY = pointsY.get(1);

            System.out.println(remainderPointX + " " + remainderPointY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets perimeter.
     * <a href="https://www.acmicpc.net/problem/15894">...</a>
     */
    public void getPerimeter() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            long n = Long.parseLong(br.readLine());

            System.out.println(n * 3 + n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 내 땅 구하기
     * <a href="https://www.acmicpc.net/problem/9063">...</a>
     */
    static public void calcMyLand() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(br.readLine());

            int minX = 10001;
            int maxX = -10001;
            int minY = 10001;
            int maxY = -10001;

            int area = 0;

            for (int i = 1; i <= n; i++) {
                int[] x = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                if (n == 1) break;

                if (minX > x[0]) {
                    minX = x[0];
                }
                if (maxX < x[0]) {
                    maxX = x[0];
                }
                if (minY > x[1]) {
                    minY = x[1];
                }
                if (maxY < x[1]) {
                    maxY = x[1];
                }

                if (i == n && (minX == maxX || minY == maxY)) break;

                area = (maxX - minX) * (maxY - minY);
            }

            System.out.println(area);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets triangle shape.
     * <a href="https://www.acmicpc.net/problem/10101">...</a>
     */
    public void getTriangleShape() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int[] angles = new int[3];
            int sumAngle = 0;

            for (int i = 0; i < angles.length; i++) {
                angles[i] = Integer.parseInt(br.readLine());
                sumAngle += angles[i];
            }

            if (sumAngle != 180)
                System.out.println("Error");
            else if (angles[0] == 60 && angles[1] == 60 && angles[2] == 60)
                System.out.println("Equilateral");
            else if (angles[0] == angles[1] || angles[0] == angles[2] || angles[1] == angles[2])
                System.out.println("Isosceles");
            else
                System.out.println("Scalene");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets triangle shape v 2.
     * <a href="https://www.acmicpc.net/problem/5073">...</a>
     */
    static public void getTriangleShapeV2() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int[] angles;
            while (true) {
                angles = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                Arrays.sort(angles);

                if (angles[0] == 0 && angles[1] == 0 && angles[2] == 0)
                    break;

                if (angles[0] + angles[1] > angles[2]) {
                    if (angles[0] == angles[1] && angles[0] == angles[2])
                        System.out.println("Equilateral");
                    else if (angles[0] == angles[1] || angles[0] == angles[2] || angles[1] == angles[2])
                        System.out.println("Isosceles");
                    else
                        System.out.println("Scalene");
                } else {
                    System.out.println("Invalid");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Calc maximum triangle perimeter.
     * <a href="https://www.acmicpc.net/problem/14215">...</a>
     */
    static public void calcMaximumTrianglePerimeter() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int[] angles;
            angles = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(angles);

            if (angles[0] + angles[1] <= angles[2]) {
                angles[2] = angles[0] + angles[1] - 1;
            }
            System.out.println(Arrays.stream(angles).sum());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
