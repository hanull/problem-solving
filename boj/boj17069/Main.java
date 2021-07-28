package boj.boj17069;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long answer;
    static int[][] map;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        dp = new long[N][N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        dp[0][1][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) continue;
                if (map[i][j] == 1) continue;

                // 가로
                if (j + 1 < N && map[i][j + 1] == 0) {
                    dp[i][j + 1][0] = dp[i][j][0] + dp[i][j][2];
                }

                // 세로
                if (i + 1 < N && map[i + 1][j] == 0) {
                    dp[i + 1][j][1] = dp[i][j][1] + dp[i][j][2];
                }

                // 대각선
                if (i + 1 < N && j + 1 < N && map[i + 1][j + 1] == 0 && map[i + 1][j] == 0 && map[i][j + 1] == 0) {
                    dp[i + 1][j + 1][2] = dp[i][j][0] + dp[i][j][1] + dp[i][j][2];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            answer += dp[N - 1][N - 1][i];
        }
        System.out.println(answer);
    }


    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
