package boj.boj2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int white, blue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = stoi(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		solve(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}

	private static void solve(int x, int y, int len) {
		if (isSame(x, y, len)) {
			if (map[x][y] == 0) {
				white++;
			} else {
				blue++;
			}
			return;
		}
		solve(x, y, len / 2);
		solve(x, y + len / 2, len / 2);
		solve(x + len / 2, y, len / 2);
		solve(x + len / 2, y + len / 2, len / 2);
	}

	private static boolean isSame(int x, int y, int len) {
		int target = map[x][y];
		for (int i = x; i < x + len; i++) {
			for (int j = y; j < y + len; j++) {
				if (map[i][j] != target) {
					return false;
				}
			}
		}
		return true;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
