package boj.boj15922;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int totalDistance = 0;
        int prev = 0;
        for (int i = 0; i < N; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            if (i == 0) {
                totalDistance = end - start;
                prev = end;
                continue;
            }
            if (start < prev) {
                if (end > prev) {
                    totalDistance += end - prev;
                    prev = end;
                }
            } else {
                totalDistance += end - start;
                prev = end;
            }
        }
        System.out.println(totalDistance);
    }
}
