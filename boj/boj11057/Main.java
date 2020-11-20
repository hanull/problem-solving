package boj.boj11057;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] dp = new int[n + 1][10];
        Arrays.fill(dp[1], 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k >= 0; k--) {
                    dp[i][j] += dp[i - 1][k];
                }
                dp[i][j] %= 10007;
            }
        }
        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += dp[n][i];
        }
        System.out.println(total  % 10007);
    }
}
