package boj.boj10844;

import java.util.Scanner;

public class Main {

    static final int mod = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] dp = new int[N + 1][11];

        for (int i = 1; i <= 9; i++) dp[1][i] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    dp[i][0] = dp[i - 1][1];
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
                }
            }
        }
        long answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer += dp[N][i];
        }
        System.out.println(answer % mod);
    }
}
