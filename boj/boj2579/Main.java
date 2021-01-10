package boj.boj2579;

import java.util.Scanner;

public class Main {

    static int[] stairs;
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        stairs = new int[301];
        dp = new int[301];
        for (int i = 1; i <= n; i++) {
            stairs[i] = sc.nextInt();
        }
        dp[1] = stairs[1];
        dp[2] = stairs[1] + stairs[2];
        dp[3] = Math.max(stairs[1] + stairs[3], stairs[3] + stairs[2]);
        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] + stairs[i], stairs[i] + stairs[i - 1] + dp[i - 3]);
        }
        System.out.println(dp[n]);
    }
}
