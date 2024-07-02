package kr.sbk.baekjoon;


import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class Day1 {

    // 1
    @Test
    public void printHello() {
        System.out.print("Hello World!");
    }

    //2
    @Test
    public void addInteger() {
        Scanner sc = new Scanner(System.in);

        double i = 0, j = 0;
        if (sc.hasNextDouble()) {
            i = sc.nextDouble();
            j = sc.nextDouble();
        }
        sc.close();

        System.out.print(i + j);
        System.out.print(i - j);
        System.out.print(i * j);
        System.out.print(i / j);
        System.out.print(i % j);

    }

    //7
    @Test
    public void isUsed() {
        Scanner sc = new Scanner(System.in);

        String input = "";

        while (true) {
            input = sc.nextLine();
            if (input.length() <= 50) break;
        }

        String existId = input;

        if (existId.equals(input)) System.out.printf("%s??!", input);
    }

    //8
    @Test
    public void convertYearToOrientalYear() {
        Scanner sc = new Scanner(System.in);

        int input = 0;

        while (true) {
            if (sc.hasNextInt()) input = sc.nextInt();

            if (input >= 1000 && input <= 3000) break;
        }
        System.out.printf("%d", input - 543);
    }

    //9
    @Test
    public void compareNumbers() {
        Scanner sc = new Scanner(System.in);

        int i = 0, j = 0, k = 0;

        while (true) {
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                j = sc.nextInt();
                k = sc.nextInt();
            }

            if (checkNumberRange(i) && checkNumberRange(j) && checkNumberRange(k)) break;
        }
        System.out.println((i + j) % k);
        System.out.println(((i % k) + (j % k)) % k);
        System.out.println((i * j) % k);
        System.out.println(((i % k) * (j % k)) % k);
    }

    private static boolean checkNumberRange(int n) {
        if (n >= 2 && n <= 10000) return true;

        return false;
    }

    //10
    public void multiply() {
        Scanner sc = new Scanner(System.in);

        int i = 0, j = 0, k = 0;

        do {
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                j = sc.nextInt();
            }

        } while (!checkNumberRange2(i) || !checkNumberRange2(j));
        sc.close();

        int singleFigure = j % 100 % 10;
        int doubleFigure = j % 100 / 10;
        int tripleFigure = j / 100;

        int singleMultiply = i * singleFigure;
        int doubleMultiply = i * doubleFigure;
        int tripleMultiply = i * tripleFigure;

        System.out.println(singleMultiply);
        System.out.println(doubleMultiply);
        System.out.println(tripleMultiply);
        System.out.println(singleMultiply + (doubleMultiply * 10) + (tripleMultiply * 100));
    }

    private static boolean checkNumberRange2(int n) {
        return n >= 100 && n <= 999;
    }

    //11
    public void calculateBigInteger() {

        Scanner sc = new Scanner(System.in);

        long i = 0L, j = 0L, k = 0L;

        do {
            if (sc.hasNextLong()) {
                i = sc.nextLong();
                j = sc.nextLong();
                k = sc.nextLong();
            }

        } while (!checkNumberRange3(i) || !checkNumberRange3(j) || !checkNumberRange3(k));
        sc.close();

        System.out.print(i + j + k);
    }

    private static boolean checkNumberRange3(long n) {
        return n >= 1 && n <= 1000000000000L;
    }

    //12
    public void printCat() {
        System.out.println("\\    /\\");
        System.out.println(" )  ( ')");
        System.out.println("(  /  )");
        System.out.println(" \\(__)|");

    }

    //13
    public void printDog() {
        System.out.println("|\\_/|");
        System.out.println("|q p|   /}");
        System.out.println("( 0 )\"\"\"\\");
        System.out.println("|\"^\"`    |");
        System.out.println("||_/=\\\\__|");
    }

}
