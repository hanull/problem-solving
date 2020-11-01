package boj.boj1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, N, M;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = stoi(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());
            dp = new int[N + 1][M + 1];
            for (int i = 1; i <= M; i++) {
                dp[1][i] = i;
            }
            for (int i = 2; i <= N; i++) {
                for (int j = i; j <= M; j++) {
                    for (int k = j; k >= i; k--) {
                        dp[i][j] += dp[i-1][k - 1];
                    }
                }
            }
            sb.append(dp[N][M] + "\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
