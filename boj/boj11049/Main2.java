package boj.boj11049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

	private static final int MAX = 987654321;
	private static int[][] map;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = stoi(br.readLine());
		map = new int[N][2];
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i] , -1);
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = stoi(st.nextToken());
			int c = stoi(st.nextToken());
			map[i][0] = r;
			map[i][1] = c;
		}
		System.out.println(solve(0, N - 1));
	}

	private static int solve(int start, int end) {
		if (start == end) {
			return 0;
		}
		if (dp[start][end] != -1) {
			return dp[start][end];
		}
		dp[start][end] = MAX;
		for (int i = start; i < end; i++) {
			dp[start][end] = Math.min(dp[start][end],
				solve(start, i) + solve(i + 1, end) + map[start][0] * map[i][1] * map[end][1]);
		}
		return dp[start][end];
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
