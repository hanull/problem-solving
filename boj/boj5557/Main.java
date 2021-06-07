package boj.boj5557;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] numbers;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        numbers = new int[N];
        dp = new long[N][21];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = stoi(st.nextToken());
        }
        System.out.println(findAll(0, numbers[0]));
    }

    private static long findAll(int index, int total) {
        if (total < 0 || total > 20) return 0;
        if (index == N - 2) {
            return total == numbers[N - 1] ? 1 : 0;
        }
        if (dp[index][total] != -1) return dp[index][total];
        dp[index][total] = 0;
        return dp[index][total] += findAll(index + 1, total + numbers[index + 1]) + findAll(index + 1, total - numbers[index + 1]);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
