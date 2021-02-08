package boj.boj1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dp[i] = stoi(st.nextToken());
        }
        int max = dp[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1] + dp[i]);
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}