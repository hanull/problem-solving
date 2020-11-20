package boj.boj11052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            arr[i] = stoi(st.nextToken());
            dp[i] = arr[i];
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], arr[j] + dp[i - j]);
            }
        }
        System.out.println(dp[N]);
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}