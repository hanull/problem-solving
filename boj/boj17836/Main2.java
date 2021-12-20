package boj.boj17836;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main2 {

	static int N, M, T;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static class Node {
		int x, y, time, sword;

		public Node(int x, int y, int time, int sword) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.sword = sword;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		T = stoi(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		int answer = move();
		System.out.println(answer > T ? "Fail" : answer);
	}

	private static int move() {
		int answer = Integer.MAX_VALUE;
		Deque<Node> deque = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][2];
		deque.add(new Node(0, 0, 0, 0));
		visited[0][0][0] = true;
		while (!deque.isEmpty()) {
			Node node = deque.poll();
			int tx = node.x;
			int ty = node.y;
			int sword = node.sword;
			int time = node.time;
			if (map[tx][ty] == 2) {
				map[tx][ty] = 0;
				sword = 1;
			}
			if (tx == N - 1 && ty == M - 1) {
				answer = time;
				break;
			}
			for (int d = 0; d < 4; d++) {
				int nx = tx + dx[d];
				int ny = ty + dy[d];
				if (!isRange(nx, ny)) {
					continue;
				}
				if (visited[nx][ny][sword]) {
					continue;
				}
				if (sword == 0 && map[nx][ny] == 1) {
					continue;
				}
				visited[nx][ny][sword] = true;
				deque.add(new Node(nx, ny, time + 1, sword));
			}
		}
		return answer;
	}

	private static boolean isRange(int nx, int ny) {
		return nx < 0 || nx >= N || ny < 0 || ny >= M ? false : true;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
