package SWEA.년요일맞추기;

import java.util.Scanner;

public class Solutioin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder answer = new StringBuilder();
        int[] days = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] result = {4, 5, 6, 0, 1, 2, 3};
        for (int tc = 1; tc <= T; tc++) {
            int M = sc.nextInt();
            int D = sc.nextInt();
            int totalDays = 0;
            for (int m = 1; m < M; m++) {
                totalDays += days[m];
            }
            totalDays += D;
            totalDays--;
            answer.append("#").append(tc).append(" ").append(result[totalDays % 7]).append("\n");
        }
        System.out.println(answer);
    }
}
