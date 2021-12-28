package boj.boj14601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int K, N;
	static int[][] map;
	static int number;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		N = (int)Math.pow(2, K);
		map = new int[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken()) - 1;
		int x = N - Integer.parseInt(st.nextToken());
		map[x][y] = -1;
		solve(0, 0, N / 2);
		System.out.println(printMap());
	}

	private static void solve(int x, int y, int len) {
		if (len == 0) {
			return;
		}
		number++;
		if (isPossible(x, y,len)) {
			map[x + len - 1][y + len - 1] = number;
		}
		if (isPossible(x, y + len, len)) {
			map[x + len - 1][y + len] = number;
		}
		if (isPossible(x + len, y, len)) {
			map[x + len][y + len - 1] = number;
		}
		if (isPossible(x + len, y + len, len)) {
			map[x + len][y + len] = number;
		}
		solve(x, y, len / 2);
		solve(x, y + len, len / 2);
		solve(x + len, y, len / 2);
		solve(x + len, y + len, len / 2);
	}

	private static boolean isPossible(int x, int y, int len) {
		for (int i = x; i < x + len; i++) {
			for (int j = y; j < y + len; j++) {
				if (map[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	private static String printMap() {
		StringBuilder answer = new StringBuilder();
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == -1) {
					count++;
				}
				answer.append(map[i][j]);
				if (j < N - 1) {
					answer.append(" ");
				}
			}
			answer.append("\n");
		}
		return count > 1 ? "-1" : answer.toString();
	}
}
