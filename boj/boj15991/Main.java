package boj.boj15991;

import java.util.Scanner;

public class Main {

    static final int MAX = 100001;
    static final long div = 1000000009;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        long[] dp = new long[MAX];
        dp[0] = dp[1] = 1;
        dp[2] = dp[3] = 2;
        dp[4] = dp[5] = 3;
        for (int i = 6; i < MAX; i++) {
            dp[i] = ((dp[i - 2] + dp[i - 4]) % div + dp[i - 6]) % div;
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            answer.append(dp[n]).append("\n");
        }
        System.out.print(answer);
    }
}
