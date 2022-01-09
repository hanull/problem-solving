package boj.boj13902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main2 {

	private static final int MAX = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		int M = stoi(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] wok = new int[M];
		Set<Integer> numbers = new HashSet<>();
		int[] dp = new int[N + 1];
		Arrays.fill(dp, MAX);
		for (int i = 0; i < M; i++) {
			wok[i] = stoi(st.nextToken());
			if (!numbers.contains(wok[i])) {
				numbers.add(wok[i]);
				dp[wok[i]] = 1;
			}
		}
		Arrays.sort(wok);
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				if (wok[i] + wok[j] > N) {
					break;
				}
				if (!numbers.contains(wok[i] + wok[j])) {
					numbers.add(wok[i] + wok[j]);
					dp[wok[i] + wok[j]] = 1;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (dp[i] == 1) {
				continue;
			}
			for (Integer number : numbers) {
				if (i - number > 0) {
					dp[i] = Math.min(dp[i], dp[i - number] + 1);
				}
			}
		}
		System.out.println(dp[N] == MAX ? -1 : dp[N]);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
