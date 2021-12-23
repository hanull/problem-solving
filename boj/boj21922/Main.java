package boj.boj21922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map, answerMap;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static class Node {
		int x, y, direction;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][M];
		answerMap = new int[N][M];
		List<Node> airConditioners = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stoi(st.nextToken());
				if (map[i][j] == 9)
					airConditioners.add(new Node(i, j));
			}
		}
		boolean[][][] visited = new boolean[N][M][4];
		for (Node airConditioner : airConditioners) {
			turnOnAirConditioner(airConditioner.x, airConditioner.y, visited);
		}
		System.out.println(calculate());
	}

	private static int calculate() {
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (answerMap[i][j] == -1) {
					answer++;
				}
			}
		}
		return answer;
	}

	private static void turnOnAirConditioner(int x, int y, boolean[][][] visited) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(x, y, 0));
		queue.add(new Node(x, y, 1));
		queue.add(new Node(x, y, 2));
		queue.add(new Node(x, y, 3));
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int tx = node.x;
			int ty = node.y;
			int direction = node.direction;
			answerMap[tx][ty] = -1;
			int nx = tx + dx[direction];
			int ny = ty + dy[direction];
			if (!isPossibleMoveToNextPoint(visited, direction, nx, ny)) {
				continue;
			}
			visited[nx][ny][direction] = true;
			queue.add(new Node(nx, ny, turn(direction, map[nx][ny])));
		}
	}

	private static int turn(int direction, int stuff) {
		if (stuff == 1) {
			if (direction == 3) {
				return 2;
			}
			if (direction == 2) {
				return 3;
			}
		}
		if (stuff == 2) {
			if (direction == 0) {
				return 1;
			}
			if (direction == 1) {
				return 0;
			}
		}
		if (stuff == 3) {
			if (direction == 0) {
				return 3;
			}
			if (direction == 1) {
				return 2;
			}
			if (direction == 2) {
				return 1;
			}
			if (direction == 3) {
				return 0;
			}
		}
		if (stuff == 4) {
			if (direction == 0) {
				return 2;
			}
			if (direction == 1) {
				return 3;
			}
			if (direction == 2) {
				return 0;
			}
			if (direction == 3) {
				return 1;
			}
		}
		return direction;
	}

	private static boolean isPossibleMoveToNextPoint(boolean[][][] visited, int direction, int nx, int ny) {
		if (!isRange(nx, ny))
			return false;
		if (visited[nx][ny][direction])
			return false;
		if (map[nx][ny] == 9)
			return false;
		return true;
	}

	private static boolean isRange(int nx, int ny) {
		return nx < 0 || nx >= N || ny < 0 || ny >= M ? false : true;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
