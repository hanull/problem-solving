package boj.boj5913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map = new int[5][5];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int answer, targetCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int K = stoi(br.readLine());
		targetCount = 25 - K;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken()) - 1;
			int y = stoi(st.nextToken()) - 1;
			map[x][y] = -1;
		}
		map[0][0] = 1;
		solve(0, 0, 1);
		System.out.println(answer);
	}

	private static void solve(int x, int y, int count) {
		if (x == 4 && y == 4) {
			if (count == targetCount) {
				answer++;
			}
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!isRange(nx, ny) || map[nx][ny] != 0) {
				continue;
			}
			setMap(nx, ny, 1);
			solve(nx, ny, count + 1);
			setMap(nx, ny, 0);
		}
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < 5 && y >= 0 && y < 5;
	}

	private static void setMap(int nx, int ny, int number) {
		map[nx][ny] = number;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
