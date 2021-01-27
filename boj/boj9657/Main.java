package boj.boj9657;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        boolean[] dp = new boolean[1001];
        dp[1] = dp[3] = dp[4] = true;
        for (int i = 5; i <= N; i++) {
            if (dp[i - 1] && dp[i - 3] && dp[i - 4]) {
                dp[i] = false;
            } else {
                dp[i] = true;
            }
        }
        if (dp[N]) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}
