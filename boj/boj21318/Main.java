package boj.boj21318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(st.nextToken());
        }
        int[] dp = new int[N];
        for (int i = 1; i < N; i++) {
            if (arr[i] < arr[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        StringBuilder answer = new StringBuilder();
        int Q = stoi(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken()) - 1;
            int y = stoi(st.nextToken()) - 1;
            answer.append(dp[y] - dp[x]).append("\n");
        }
        System.out.print(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
