package boj.boj18427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int MOD = 10007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		int M = stoi(st.nextToken());
		int H = stoi(st.nextToken());
		int[][] dp = new int[N + 1][H + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			dp[i - 1][0] = 1;
			while (st.hasMoreTokens()) {
				int height = stoi(st.nextToken());
				for (int h = height; h <= H; h++) {
					dp[i][h] += dp[i - 1][h - height];
					dp[i][h] %= MOD;
				}
			}
			for (int h = 1; h <= H; h++) {
				dp[i][h] += dp[i - 1][h];
				dp[i][h] %= MOD;
			}
		}
		System.out.println(dp[N][H]);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
