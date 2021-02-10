package boj.boj10826;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        BigInteger[] dp = new BigInteger[N + 1];
        if (N == 0) {
            System.out.println(0);
            System.exit(0);
        } else if (N == 1) {
            System.out.println(1);
            System.exit(0);
        }
        dp[1] = BigInteger.valueOf(1);
        dp[2] = BigInteger.valueOf(1);
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2]);
        }
        System.out.println(dp[N]);
    }

}
