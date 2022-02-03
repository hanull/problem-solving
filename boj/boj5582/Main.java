package boj.boj5582;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] strA = br.readLine().toCharArray();
		char[] strB = br.readLine().toCharArray();
		int[][] dp = new int[strA.length + 1][strB.length + 1];
		int answer = 0;
		for (int i = 1; i <= strA.length; i++) {
			for (int j = 1; j <= strB.length; j++) {
				if (strA[i - 1] == strB[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				answer = Math.max(answer, dp[i][j]);
			}
		}
		System.out.println(answer);
	}
}
