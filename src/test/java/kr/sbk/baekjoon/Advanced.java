package kr.sbk.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Advanced {

    public void findWhatsMissing() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> fullSet = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 2, 8));

        List<Integer> inputSet = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList()); //java 21에서는 toList()로 치환 가능

        for (int i = 0; i < inputSet.size(); i++) {
            int a = fullSet.get(i) - inputSet.get(i);
            System.out.print(a + " ");
        }
    }

    /*
     *
     ***
     *****
     *******
     *********
     *******
     *****
     ***
     *
     */
    public void printStartByInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());

        int count = input * 2 - 1;
        int space = input - 1;

        for (int i = 1; i <= count; i++) {
            int stars = i <= input ? i : count - i + 1;
            int spaces = i <= input ? space-- : space++;

            printSpaces(spaces);
            printStars(stars);
            System.out.println();
        }
    }

    private void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    private void printStars(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print("*");
        }
    }

    public void printStarByInput2() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(br.readLine());
        int count = input * 2 - 1; // 5*2-1 = 9
        int space = input - 1;

        for (int i = 1; i <= count; i += 2) {
            for (int j = 0; j < space; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < i; k++) {
                System.out.print("*");
            }
            space--;
            System.out.println();
        }

        for (int i = count - 1; i >= 1; i -= 2) {
            space++;
            for (int j = 0; j <= space; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void isPalindrome() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();

        int judge = 1;

        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                judge = 0;
                break;
            }
        }

        System.out.println(judge);

    }

    /*
    문제
알파벳 대소문자로 된 단어가 주어지면, 이 단어에서 가장 많이 사용된 알파벳이 무엇인지 알아내는 프로그램을 작성하시오. 단, 대문자와 소문자를 구분하지 않는다.

입력
첫째 줄에 알파벳 대소문자로 이루어진 단어가 주어진다. 주어지는 단어의 길이는 1,000,000을 넘지 않는다.

출력
첫째 줄에 이 단어에서 가장 많이 사용된 알파벳을 대문자로 출력한다. 단, 가장 많이 사용된 알파벳이 여러 개 존재하는 경우에는 ?를 출력한다.

     */
    public void wordStudy() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine().toUpperCase();

        Map<Character, Integer> charCount = new HashMap<>();

        for (char c : word.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        char mostFrequentChar = ' ';
        int maxCount = 0;


        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() == maxCount) {
                mostFrequentChar = '?';
                continue;
            }
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentChar = entry.getKey();
            }
        }

        System.out.println(mostFrequentChar);
    }

    public void countCroatiaAlphabet() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] croatiaAlphabet = br.readLine()
                .replaceAll("c=|c-|dz=|d-|lj|nj|s=|z=", "0").toCharArray();

        System.out.println(croatiaAlphabet.length);
    }

    public void checkGroupWord() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int groupWordCount = 0;
        for (int j = 0; j < n; j++) {
            List<Character> word = br.readLine().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            List<Character> containsList = new ArrayList<>();

            List<Character> dupWord = new ArrayList<>();
            dupWord.add(word.get(0));
            for (int i = 1; i < word.size(); i++) {
                if (word.get(i) != word.get(i - 1)) {
                    dupWord.add(word.get(i));
                }
            }


            boolean check = true;
            for (int i = 0; i < dupWord.size(); i++) {
                if (!containsList.contains(dupWord.get(i))) containsList.add(dupWord.get(i));
                else {
                    check = false;
                    break;
                }
            }
//            containsList.forEach(System.out::print);
//            System.out.println("\n"+check);
            if (check) groupWordCount++;
        }
        System.out.println(groupWordCount);

    }

    public void myGrade() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int registeredCourses = 20;

        List<Double> grade = new ArrayList<>();
        List<Double> credit = new ArrayList<>();

        Map<String, Double> gradeScore = new HashMap<String, Double>() {
            {
                put("A+", 4.5);
                put("A0", 4.0);
                put("B+", 3.5);
                put("B0", 3.0);
                put("C+", 2.5);
                put("C0", 2.0);
                put("D+", 1.5);
                put("D0", 1.0);
                put("F", 0.0);
            }
        };

        //Since JAVA 9
        /*Map<String, Double> gradeScore = Map.of(
                "A+", 4.5,
                "A0", 4.0,
                "B+", 3.5,
                "B0", 3.0,
                "C+", 2.5,
                "C0", 2.0,
                "D+", 1.5,
                "D0", 1.0,
                "F", 0.0
        );*/

        //과목 평점 입력
        for (int i = 0; i < registeredCourses; i++) {
            String[] MajorScore = br.readLine().split(" ");

            if ("P".equals(MajorScore[2])) continue;

            grade.add(Double.parseDouble(MajorScore[1]));

            for (Map.Entry<String, Double> entry : gradeScore.entrySet()) {
                if (MajorScore[2].equals(entry.getKey())) credit.add(entry.getValue());
            }
        }

        double sumGrade = grade.stream().mapToDouble(Double::doubleValue).sum();

        double timesMyGrade = 0.0;
        for (int i = 0; i < credit.size(); i++) {
            timesMyGrade += grade.get(i) * credit.get(i);
        }
        double myTotalGrade = timesMyGrade / sumGrade;

//        System.out.println(sumGrade);
//        System.out.println(timesMyGrade);
        System.out.println(myTotalGrade);

    }

    public void twoDimensionMatrix() throws Exception {
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
}
