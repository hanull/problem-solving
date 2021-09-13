package boj.boj11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(st.nextToken());
        }

        int[] dp = new int[N];
        dp[0] = 1;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] > max) {
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
