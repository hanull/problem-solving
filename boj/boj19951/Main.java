package boj.boj19951;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		int M = stoi(st.nextToken());
		int[] height = new int[N + 1];
		int[] arr = new int[N + 2];
		int[] sum = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			height[i] = stoi(st.nextToken());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = stoi(st.nextToken());
			int end = stoi(st.nextToken());
			int k = stoi(st.nextToken());
			arr[start] += k;
			arr[end + 1] -= k;
		}
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sum[i] = sum[i - 1] + arr[i];
			height[i] += sum[i];
			answer.append(height[i]);
			if (i < N) {
				answer.append(" ");
			}
		}
		System.out.print(answer);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
