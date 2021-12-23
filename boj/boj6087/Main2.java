package boj.boj6087;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {

	static int N, M;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static class Node {
		int x, y, direction, count;

		public Node(int x, int y, int direction, int count) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.count = count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		int startX = 0, startY = 0, endX = 0, endY = 0;
		int index = 0;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'C') {
					map[i][j] = '.';
					if (index == 0) {
						startX = i;
						startY = j;
						index++;
					} else {
						endX = i;
						endY = j;
					}
				}
			}
		}
		System.out.println(installMirror(startX, startY, endX, endY));
	}

	private static int installMirror(int startX, int startY, int endX, int endY) {
		int answer = 0;
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.count));
		boolean[][][] visited = new boolean[N][M][4];
		priorityQueue.add(new Node(startX, startY, -1, 0));
		while (!priorityQueue.isEmpty()) {
			Node node = priorityQueue.poll();
			int tx = node.x;
			int ty = node.y;
			int direction = node.direction;
			int count = node.count;
			if (tx == endX && ty == endY) {
				answer = count;
				break;
			}
			if (direction != -1) {
				if (visited[tx][ty][direction]) continue;
				visited[tx][ty][direction] = true;
			}
			for (int d = 0; d < 4; d++) {
				int nx = tx + dx[d];
				int ny = ty + dy[d];
				if (!isRange(nx, ny)) {
					continue;
				}
				if (visited[nx][ny][d]) {
					continue;
				}
				if (map[nx][ny] == '*') {
					continue;
				}
				if (direction == -1) {
					priorityQueue.add(new Node(nx, ny, d, 0));
				} else {
					priorityQueue.add(new Node(nx, ny, d, count + (direction == d ? 0 : 1)));
				}
			}
		}
		return answer;
	}

	private static boolean isRange(int nx, int ny) {
		return nx < 0 || nx >= N || ny < 0 || ny >= M ? false : true;
	}
}
