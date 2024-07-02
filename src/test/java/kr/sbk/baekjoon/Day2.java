package kr.sbk.baekjoon;

import java.util.Scanner;

public class Day2 {

    //1
    public void getDiff() {

        Scanner sc = new Scanner(System.in);

        int i = 0, j = 0;

        if (sc.hasNextInt()) {
            i = sc.nextInt();
            j = sc.nextInt();
        }

        String sent = "";

        if (i > j) {
            sent = ">";
        } else if (i == j) {
            sent = "==";
        } else {
            sent = "<";
        }

        System.out.printf("%s", sent);
    }

    //3
    public void checkMoonYear() {

        Scanner sc = new Scanner(System.in);

        int i = 0, j = 0;
        String grade = "";

        if (sc.hasNextInt()) {
            i = sc.nextInt();
//            j = sc.nextInt();
        }

        grade = "0";
        if (i % 4 == 0 && (i % 100 != 0 || i % 400 == 0))
            grade = "1";

        System.out.printf("%s", grade);
    }

    //4
    public void checkPosition() {

        Scanner sc = new Scanner(System.in);

        int i = 0, j = 0;
        String grade = "";

        if (sc.hasNextInt()) {
            i = sc.nextInt();
            j = sc.nextInt();
        }

        if (i > 0 && j > 0) {
            System.out.println("1");

        } else if (i < 0 && j > 0) {
            System.out.println("2");

        } else if (i < 0 && j < 0) {
            System.out.println("3");

        } else if (i > 0 && j < 0) {
            System.out.println("4");

        } else {
            System.out.println("0,0");
        }
    }

    //5
    public void alarm() {


        Scanner sc = new Scanner(System.in);

        int h = 0, m = 0;
        String grade = "";

        if (sc.hasNextInt()) {
            h = sc.nextInt();
            m = sc.nextInt();
        }

        m = m - 45;
        if (m < 0) {
            m = 60 + m;
            h = h - 1;
        }
        h = (h < 0 ? 23 : h);

        System.out.printf("%d %d", h, m);

    }

    //alarm2
    public void alarm2() {
        Scanner sc = new Scanner(System.in);

        int h = 0, m = 0, s = 0;
        String grade = "";

        if (sc.hasNextInt()) {
            h = sc.nextInt();
            m = sc.nextInt();
            s = sc.nextInt();
        }

        m = m + s;
        while (m >= 60) {
            m = m - 60;
            h = h + 1;
        }

        h = (h > 23 ? h % 24 : h);

        System.out.printf("%d %d", h, m);

    }

    //7
    public void calcDice() {

        Scanner sc = new Scanner(System.in);

        int i = 0, j = 0, k = 0;

        if (sc.hasNextInt()) {
            i = sc.nextInt();
            j = sc.nextInt();
            k = sc.nextInt();
        }

        int max = i;
        int price = 100;
        int bonus = 0;

        if (assertInt(i, j) + assertInt(i, k) == 2) {
            price = price * 10;
            bonus += 10000;

        } else if (assertInt(i, j) + assertInt(i, k) == 1) {
            bonus += 1000;

        } else if (assertInt(j, k) == 1) {
            bonus += 1000;
            max = j;

        } else {
            if (max < j) max = j;
            if (max < k) max = k;
        }
        System.out.println(bonus + (max * price));

    }

    private static int assertInt(int i, int j) {
        if (i == j) return 1;

        return 0;

    }
}
