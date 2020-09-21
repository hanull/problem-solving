package boj.boj2225;

import java.util.Scanner;

public class Main {

    static int n, k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        int[][] dp = new int[k + 1][n + 1];

        for (int j = 0; j <= n; j++) {
            dp[1][j] = 1;
        }
        for (int i = 2; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                for (int l = 0; l <= j; l++) {
                    dp[i][j] += dp[i - 1][j - l];
                    dp[i][j] %= 1000000000;
                }
            }
        }
        System.out.println(dp[k][n]);
    }
}
