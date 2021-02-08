package boj.boj11660;

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
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i][j - 1] + stoi(st.nextToken());
            }
        }
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = stoi(st.nextToken());
            int y1 = stoi(st.nextToken());
            int x2 = stoi(st.nextToken());
            int y2 = stoi(st.nextToken());
            int total = 0;
            for (int i = x1; i <= x2; i++) {
                total += dp[i][y2] - dp[i][y1 - 1];
            }
            sb.append(total + "\n");
        }
        System.out.print(sb);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
