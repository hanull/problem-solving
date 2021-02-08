package boj.boj1003;

import java.util.Scanner;

public class Main {

    static int[][] dp = new int[41][41];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        init();
        while (T-- > 0) {
            int N = sc.nextInt();
            System.out.println(dp[N][0] + " " + dp[N][1]);
        }
    }

    static void init() {
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;
        dp[2][0] = 1;
        dp[2][1] = 1;

        for (int i = 3; i <= 40; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }
    }

}
