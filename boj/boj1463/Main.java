package boj.boj1463;

import java.util.Scanner;

public class Main {
    static int n;
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp = new int[n + 1];
        initDP(dp, n);
        System.out.println(dp[n]);
    }

    private static void initDP(int[] dp, int n) {
        for (int i = 2; i <= n; i++) {
            if (i % 3 == 0) dp[i] = dp[i / 3] + 1;
            else if (i % 2 == 0) dp[i] = dp[i / 2] + 1;
            else dp[i] = dp[i - 1] + 1;
            dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
        }
    }
}
