package boj.boj1463;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        Arrays.fill(dp, n);
        dp[n] = 0;

        for (int i = n - 1; i >= 1; i--) {
            dp[i] = dp[i + 1] + 1;
            if (i * 3 <= n) dp[i] = Math.min(dp[i], dp[i * 3] + 1);
            if (i * 2 <= n) dp[i] = Math.min(dp[i], dp[i * 2] + 1);
        }
        System.out.println(dp[1]);
    }
}
