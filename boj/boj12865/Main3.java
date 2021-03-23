package boj.boj12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken());
        int[][] dp = new int[N + 1][K + 1];
        for (int index = 1; index <= N; index++) {
            st = new StringTokenizer(br.readLine());
            int weight = stoi(st.nextToken());
            int value = stoi(st.nextToken());
            for (int bagWeight = 1; bagWeight <= K; bagWeight++) {
                if (weight <= bagWeight) {
                    dp[index][bagWeight] = Math.max(value + dp[index - 1][bagWeight - weight], dp[index - 1][bagWeight]);
                } else {
                    dp[index][bagWeight] = dp[index - 1][bagWeight];
                }
            }
        }
        System.out.println(dp[N][K]);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
