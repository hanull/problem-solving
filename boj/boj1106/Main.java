package boj.boj1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int target = stoi(st.nextToken());
        int N = stoi(st.nextToken());
        int[] cost = new int[N];
        int[] customers = new int[N];
        int[] dp = new int[1101];
        Arrays.fill(dp, MAX);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = stoi(st.nextToken());
            customers[i] = stoi(st.nextToken());
            dp[customers[i]] = cost[i];
        }
        dp[0] = 0;
        for (int i = 1; i <= 1100; i++) {
            for (int j = 0; j < N; j++) {
                if (i < customers[j]) continue;
                dp[i] = Math.min(dp[i], dp[i - customers[j]] + cost[j]);
            }
        }
        int answer = MAX;
        for (int i = target; i <= 1100; i++) {
            answer = Math.min(dp[i], answer);
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
