package boj.boj2302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = stoi(br.readLine());
		int M = stoi(br.readLine());
		int[] dp = new int[N + 1];
		boolean[] vip = new boolean[N + 1];
		for (int i = 0; i < M; i++) {
			vip[stoi(br.readLine())] = true;
		}
		dp[0] = dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			if (vip[i] || vip[i - 1]) {
				dp[i] = dp[i - 1];
				continue;
			}
			dp[i] = dp[i - 2] + dp[i - 1];
		}
		System.out.println(dp[N]);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
