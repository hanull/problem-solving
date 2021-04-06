package boj.boj1915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        dp = new int[N + 1][M + 1];

        int maxLength = 0;
        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= M; j++) {
                dp[i][j] = input.charAt(j - 1) - '0';
                if (dp[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        System.out.println(maxLength * maxLength);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
