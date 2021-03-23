package boj.boj1463;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] dp = new int[N + 1];
        Arrays.fill(dp, N + 1);

        dp[N] = 0;
        for (int i = N; i >= 1; i--) {
            dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
            if (i % 3 == 0) dp[i / 3] = Math.min(dp[i / 3], dp[i] + 1);
            if (i % 2 == 0) dp[i / 2] = Math.min(dp[i / 2], dp[i] + 1);
        }
        System.out.println(dp[1]);
    }
}
