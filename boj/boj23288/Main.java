package boj.boj23288;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static int[][] map;
	static int[] dx = {0, 0, 1, -1};	// 동서남북
	static int[] dy = {1, -1, 0, 0};
	static int[] dice = {1, 2, 3, 4, 5, 6};
	static class Node {
		int x, y, direction;

		public Node(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		System.out.println(move());
	}

	private static int move() {
		int answer = 0;
		Node currentNode = new Node(0, 0, 0);
		for (int i = 0; i < K; i++) {
			int tx = currentNode.x;
			int ty = currentNode.y;
			int direction = currentNode.direction;
			// 이동할 수 없다면, 반대로 방향으로 회전
			if (!isValidMove(tx + dx[direction], ty + dy[direction])) {
				direction = turnInOppositeDirection(direction);
			}
			int nx = tx + dx[direction];
			int ny = ty + dy[direction];
			// 주사위 이동
			moveDice(direction);
			// 점수 획득
			answer += getPoint(nx, ny);
			// 다음 이동 방향 결정
			direction = changeDirection(dice[5], map[nx][ny], direction);
			setNextNode(currentNode, nx, ny, direction);
		}
		return answer;
	}

	private static void setNextNode(Node currentNode, int nx, int ny, int direction) {
		currentNode.x = nx;
		currentNode.y = ny;
		currentNode.direction = direction;
	}

	private static int changeDirection(int diceBottomNumber, int mapNumber, int direction) {
		if (diceBottomNumber == mapNumber) {
			return direction;
		}
		if (diceBottomNumber > mapNumber) {
			return turnRight(direction);
		}
		return turnLeft(direction);
	}

	private static int turnLeft(int direction) {
		if (direction == 0) {
			return 3;
		}
		if (direction == 1) {
			return 2;
		}
		if (direction == 2) {
			return 0;
		}
		return 1;
	}

	private static int turnRight(int direction) {
		if (direction == 0) {
			return 2;
		}
		if (direction == 1) {
			return 3;
		}
		if (direction == 2) {
			return 1;
		}
		return 0;
	}

	private static int getPoint(int x, int y) {
		int count = 0;
		int targetNumber = map[x][y];
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		queue.add(new Node(x, y));
		visited[x][y] = true;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int tx = node.x;
			int ty = node.y;
			if (map[tx][ty] == targetNumber) {
				count++;
			}
			for (int d = 0; d < 4; d++) {
				int nx = tx + dx[d];
				int ny = ty + dy[d];
				if (!isValidMove(nx, ny)) {
					continue;
				}
				if (visited[nx][ny]) {
					continue;
				}
				if (map[nx][ny] != targetNumber) {
					continue;
				}
				visited[nx][ny] = true;
				queue.add(new Node(nx, ny));
			}
		}
		return targetNumber * count;
	}

	private static void moveDice(int direction) {
		if (direction == 0) {
			int temp = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[2];
			dice[2] = temp;
		}
		if (direction == 1) {
			int temp = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[5];
			dice[5] = dice[3];
			dice[3] = temp;
		}
		if (direction == 2) {
			int temp = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[4];
			dice[4] = temp;
		}
		if (direction == 3) {
			int temp = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[1];
			dice[1] = temp;
		}
	}

	private static int turnInOppositeDirection(int direction) {
		if (direction == 0) {
			return 1;
		}
		if (direction == 1) {
			return 0;
		}
		if (direction == 2) {
			return 3;
		}
		return 2;
	}

	private static boolean isValidMove(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < M;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
