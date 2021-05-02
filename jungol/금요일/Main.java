package jungol.금요일;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] days = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] answer = new int[7];
        int totalDays = 13;
        for (int year = 1900; year < 1900 + N; year++) {
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) days[1] = 29;
            else days[1] = 28;
            for (int j = 0; j < 12; j++) {
                answer[(totalDays - 1) % 7]++;
                totalDays += days[j];
            }
        }
        for (int i = 0; i < 7; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
