package boj.boj10830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private final int MOD = 1000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		long B = stol(st.nextToken());
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

			}
		}
	}

	private static long stol(String input) {
		return Long.parseLong(input);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
