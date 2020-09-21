package boj.boj1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                dp[i][j] = stoi(st.nextToken());
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] += dp[i-1][j];
                } else if (i == j) {
                    dp[i][j] += dp[i - 1][j - 1];
                } else {
                    dp[i][j] += Integer.max(dp[i - 1][j], dp[i - 1][j - 1]);
                }
            }
        }
        System.out.println(getMax(dp, n));
    }

    private static int getMax(int[][] dp, int n) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (res < dp[n - 1][i]) {
                res = dp[n-1][i];
            }
        }
        return res;
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
