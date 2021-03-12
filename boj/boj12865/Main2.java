package boj.boj12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken());
        int[] weight = new int[N + 1];
        int[] value = new int[N + 1];
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = stoi(st.nextToken());
            value[i] = stoi(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= K; w++) {
                if (weight[i] <= w) {     // 현재 물건을 반드시 포함하면서, 해당 무게의 배낭에 담을 수 있는 것
                    dp[i][w] = Math.max(value[i] + dp[i - 1][w - weight[i]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        System.out.println(dp[N][K]);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
