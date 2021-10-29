package boj.boj1695;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        arr = new int[N];
        dp = new int[N][N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(st.nextToken());
        }
        System.out.println(palindrome(0, N - 1));
    }

    private static int palindrome(int start, int end) {
        if (start >= end) return 0;

        int answer = dp[start][end];
        if (answer > 0) return answer;

        if (arr[start] == arr[end]) {
            answer = palindrome(start + 1, end - 1);
        } else {
            answer = Math.min(palindrome(start + 1, end), palindrome(start, end - 1)) + 1;
        }
        return dp[start][end] = answer;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
