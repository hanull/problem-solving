package boj.boj20947;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int N;
	static char[][] map;
	static int[][] countMap;
	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static boolean[][] notBomb;
	static Queue<Node> lists = new ArrayDeque<>();
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		countMap = new int[N][N];
		notBomb = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'O') {
					lists.add(new Node(i, j));
				}
			}
		}
		checkImpossiblePoint();
		makeAnswerMap();
	}

	private static void makeAnswerMap() {
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '.' && !notBomb[i][j]) {
					answer.append('B');
				} else {
					answer.append(map[i][j]);
				}
			}
			answer.append('\n');
		}
		System.out.print(answer);
	}

	private static void checkImpossiblePoint() {
		while (!lists.isEmpty()) {
			Node node = lists.poll();
			int tx = node.x;
			int ty = node.y;
			for (int d = 0; d < 4; d++) {
				int nx = tx + dx[d];
				int ny = ty + dy[d];
				while (isMove(nx, ny)) {
					notBomb[nx][ny] = true;
					nx += dx[d];
					ny += dy[d];
				}
			}
		}
	}

	private static boolean isMove(int nx, int ny) {
		if (!isRange(nx, ny)) {
			return false;
		}
		return map[nx][ny] == '.';
	}

	private static boolean isRange(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < N;
	}
}
