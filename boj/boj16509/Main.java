package boj.boj16509;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map = new int[10][9];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[] diagonalX = {-1, -1, 1, 1};
	static int[] diagonalY = {-1, 1, -1, 1};
	static int[][] nextDiagonalMove = {{0, 1}, {2, 3}, {0, 2}, {1, 3}};

	static class Node {
		int x, y, count;

		public Node(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int startX = stoi(st.nextToken());
		int startY = stoi(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int endX = stoi(st.nextToken());
		int endY = stoi(st.nextToken());
		map[endX][endY] = 1;
		System.out.println(move(startX, startY, endX, endY));
	}

	private static int move(int startX, int startY, int endX, int endY) {
		int answer = Integer.MAX_VALUE;
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[10][9];
		queue.add(new Node(startX, startY, 0));
		visited[startX][startY] = true;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int tx = node.x;
			int ty = node.y;
			int count = node.count;
			if (tx == endX && ty == endY) {
				answer = count;
				break;
			}
			for (int d = 0; d < 4; d++) {
				int nx = tx + dx[d];
				int ny = ty + dy[d];
				if (!isRange(nx, ny) || map[nx][ny] == 1) {
					continue;
				}
				// 대각선 이동
				for (int diagonalDirection : nextDiagonalMove[d]) {
					int nextX = nx;
					int nextY = ny;
					if (isPossibleMove(nextX, nextY, diagonalDirection)) {
						nextX += diagonalX[diagonalDirection] * 2;
						nextY += diagonalY[diagonalDirection] * 2;
						if (!visited[nextX][nextY]) {
							visited[nextX][nextY] = true;
							queue.add(new Node(nextX, nextY, count + 1));
						}
					}
				}
			}
		}
		return answer == Integer.MAX_VALUE ? -1 : answer;
	}

	private static boolean isPossibleMove(int x, int y, int d) {
		int nx = x + diagonalX[d];
		int ny = y + diagonalY[d];
		if (!isRange(nx, ny) || map[nx][ny] == 1 || !isRange(nx + diagonalX[d], ny + diagonalY[d])) {
			return false;
		}
		return true;
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= 10 || y < 0 || y >= 9 ? false : true;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
