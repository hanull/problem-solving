package boj.boj15724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        int[][] map = new int[N + 1][M + 1];
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + map[i][j];
            }
        }

        int K = stoi(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = stoi(st.nextToken());
            int y1 = stoi(st.nextToken());
            int x2 = stoi(st.nextToken());
            int y2 = stoi(st.nextToken());
            int total = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
            answer.append(total).append("\n");
        }
        System.out.print(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
