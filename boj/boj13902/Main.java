package boj.boj13902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		int M = stoi(st.nextToken());
		int[] dp = new int[N + 1];
		int[] sizes = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			sizes[i] = stoi(st.nextToken());
		}
		Arrays.sort(sizes);
		Set<Integer> possibleSizes = new HashSet<>();
		for (int i = 0; i < sizes.length; i++) {
			possibleSizes.add(sizes[i]);
			for (int j = i + 1; j < sizes.length; j++) {
				if (sizes[i] + sizes[j] > N) {
					break;
				}
				possibleSizes.add(sizes[i] + sizes[j]);
			}
		}
		Arrays.fill(dp, Integer.MAX_VALUE);
		for (Integer targetSize : possibleSizes) {
			dp[targetSize] = 1;
		}
		for (int size = sizes[0]; size <= N; size++) {
			for (Integer possibleSize : possibleSizes) {
				if (size - possibleSize > 0 && dp[size - possibleSize] != Integer.MAX_VALUE) {
					dp[size] = Math.min(dp[size], dp[size - possibleSize] + 1);
				}
			}
		}
		System.out.println(dp[N] == Integer.MAX_VALUE ? -1 : dp[N]);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
