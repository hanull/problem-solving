package boj.boj1051;

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
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Character.getNumericValue(input.charAt(j));
			}
		}
		int answer = 0;
		int maxLength = Math.min(N, M);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int len = maxLength; len >= 0; len--) {
					if (isOutOfRange(N, M, i, j, len)) {
						continue;
					}
					if (isPossible(map, i, j, len - 1)) {
						answer = Math.max(answer, len * len);
						break;
					}
				}
				if (answer == maxLength * maxLength) {
					break;
				}
			}
		}
		System.out.println(answer);
	}

	private static boolean isOutOfRange(int N, int M, int i, int j, int len) {
		return i + len - 1 >= N || j + len - 1 >= M;
	}

	private static boolean isPossible(int[][] map, int i, int j, int len) {
		return map[i][j] == map[i][j + len] && map[i][j] == map[i + len][j] && map[i][j] == map[i + len][j + len];
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
