package boj.boj2725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	private static final int MAX = 1000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(br.readLine());
		int[] dp = new int[MAX + 1];
		dp[1] = 3;
		for (int i = 2; i <= MAX; i++) {
			int count = 0;
			for (int j = 1; j < i; j++) {
				if (gcd(i, j) == 1) {
					count++;
				}
			}
			dp[i] = dp[i - 1] + count * 2;
		}
		StringBuilder answer = new StringBuilder();
		while (T-- > 0) {
			int n = stoi(br.readLine());
			answer.append(dp[n]);
			if (T > 0) {
				answer.append("\n");
			}
		}
		System.out.print(answer);
	}

	private static int gcd(int i, int j) {
		BigInteger b1 = BigInteger.valueOf(i);
		BigInteger b2 = BigInteger.valueOf(j);
		return b1.gcd(b2).intValue();
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
