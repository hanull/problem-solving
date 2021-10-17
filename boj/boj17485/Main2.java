package boj.boj17485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        int[][][] dp = new int[N][M][3];
        // 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < 3; d++) {
                    dp[i][j][d] = N * M * 100;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < M; j++) {
                dp[0][j][i] = map[0][j];
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j == 0) {
                    dp[i][j][0] = Math.min(dp[i][j][0], Math.min(dp[i - 1][j + 1][1], dp[i - 1][j + 1][2]) + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i][j][1], Math.min(dp[i - 1][j][2], dp[i - 1][j][0]) + map[i][j]);
                } else if (j == M - 1) {
                    dp[i][j][1] = Math.min(dp[i][j][1], Math.min(dp[i - 1][j][2], dp[i - 1][j][0]) + map[i][j]);
                    dp[i][j][2] = Math.min(dp[i][j][2], Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][0]) + map[i][j]);
                } else {
                    dp[i][j][0] = Math.min(dp[i][j][0], Math.min(dp[i - 1][j + 1][1], dp[i - 1][j + 1][2]) + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i][j][1], Math.min(dp[i - 1][j][2], dp[i - 1][j][0]) + map[i][j]);
                    dp[i][j][2] = Math.min(dp[i][j][2], Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][0]) + map[i][j]);
                }
            }
        }
        int answer = N * M * 100;
        for (int i = 0; i < M; i++) {
            for (int d = 0; d < 3; d++) {
                answer = Math.min(answer, dp[N - 1][i][d]);
            }
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
