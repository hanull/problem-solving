package boj.boj2616;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] sum = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			sum[i] = sum[i - 1] + stoi(st.nextToken());
		}
		int M = stoi(br.readLine());
		int[][] dp = new int[4][N + 1];
		for (int i = 1; i <= 3; i++) {
			for (int j = M * i; j <= N; j++) {
				// 현재 m개의 칸을 선택하지 않는 경우
				// 이전까지의 최대값 + 현재 m개 칸을 선택한 경우
				dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - M] + sum[j] - sum[j - M]);
			}
		}
		System.out.println(dp[3][N]);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
