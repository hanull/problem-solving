package boj.boj15671;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static char[][] map = new char[6][6];
	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = stoi(br.readLine());
		for (int i = 0; i < 6; i++) {
			Arrays.fill(map[i], '.');
		}
		map[2][2] = map[3][3] = 'W';
		map[2][3] = map[3][2] = 'B';
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken()) - 1;
			int y = stoi(st.nextToken()) - 1;
			put(x, y, i);
		}
		printResult();
	}

	private static void put(int x, int y, int index) {
		char myColor, targetColor;
		if (index % 2 == 0) {
			myColor = 'B';
			targetColor = 'W';
		} else {
			myColor = 'W';
			targetColor = 'B';
		}
		map[x][y] = myColor;
		Queue<Node> q = new ArrayDeque<>();
		for (int d = 0; d < 8; d++) {
			q.clear();
			flag = false;
			int nx = x + dx[d];
			int ny = y + dy[d];
			while (isPossible(nx, ny, targetColor)) {
				q.add(new Node(nx, ny));
				nx += dx[d];
				ny += dy[d];
			}
			if (flag) {
				while (!q.isEmpty()) {
					Node node = q.poll();
					map[node.x][node.y] = myColor;
				}
			}
		}
	}

	private static boolean isPossible(int nx, int ny, char targetColor) {
		if (!isRange(nx, ny)) {
			return false;
		}
		if (map[nx][ny] != targetColor) {
			if (map[nx][ny] != '.'){ flag = true;}
			return false;
		}
		return true;
	}

	private static boolean isRange(int nx, int ny) {
		return nx < 0 || nx >= 6 || ny < 0 || ny >= 6 ? false : true;
	}

	private static void printResult() {
		int white = 0;
		int black = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (map[i][j] == 'W') white++;
				if (map[i][j] == 'B') black++;
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println(white > black ? "White" : "Black");
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
