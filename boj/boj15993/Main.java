package boj.boj15993;

import java.util.Scanner;

public class Main {

    static final long DivisionValue = 1000000009;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long[][] dp = new long[100001][2];

        // 0:홀,  1:짝
        dp[1][0] = dp[2][0] = dp[2][1] = 1;
        dp[3][0] = dp[3][1] = 2;
        for (int i = 4; i <= 100000; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 2][1] + dp[i - 3][1]) % DivisionValue;
            dp[i][1] = (dp[i - 1][0] + dp[i - 2][0] + dp[i - 3][0]) % DivisionValue;
        }

        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            System.out.println(dp[N][0] + " " + dp[N][1]);
        }
    }
}
