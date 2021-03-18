package boj.boj10942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] number;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        number = new int[N + 1];
        dp = new int[N + 1][N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            number[i] = stoi(st.nextToken());
        }

        solve();

        M = stoi(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = stoi(st.nextToken());
            int end = stoi(st.nextToken());
            sb.append(dp[start][end]).append("\n");
        }
        System.out.println(sb);
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        for (int i = 1; i <= N - 1; i++) {
            if (number[i] == number[i + 1]) dp[i][i + 1] = 1;
        }

        for (int i = 2; i < N; i++) {
            for (int j = 1; j <= N - i; j++) {
                if (number[j] == number[j + i] && dp[j + 1][j + i - 1] == 1) {
                    dp[j][j + i] = 1;
                }
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
