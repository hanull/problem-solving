package boj.boj1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int a, b, c;

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
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
	}

	private static void solve(int x, int y, int len) {
		if (isSame(x, y, len)) {
			if (map[x][y] == -1) {
				a++;
			} else if (map[x][y] == 0) {
				b++;
			} else {
				c++;
			}
			return;
		}
		int nextLength = len / 3;
		solve(x, y, nextLength);
		solve(x, y + nextLength, nextLength);
		solve(x, y + nextLength * 2, nextLength);
		solve(x + nextLength, y, nextLength);
		solve(x + nextLength, y + nextLength, nextLength);
		solve(x + nextLength, y + nextLength * 2, nextLength);
		solve(x + nextLength * 2, y, nextLength);
		solve(x + nextLength * 2, y + nextLength, nextLength);
		solve(x + nextLength * 2, y + nextLength * 2, nextLength);
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
