package boj.boj3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main4 {

	static int N, APPLE, TURN;
	static int[][] map;
	static boolean[][] checkSnake;
	static TimeAndDirection[] commands;
	static Deque<Node> snake = new ArrayDeque<>();
	static int[] dx = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	static int[] dy = {0, 0, -1, 1};
	static class TimeAndDirection {
		int time;
		char direction;

		public TimeAndDirection(int time, char direction) {
			this.time = time;
			this.direction = direction;
		}
	}
	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(moveSnake());
	}

	private static int moveSnake() {
		int answer = 0;
		int direction = 3;	// 상, 하, 좌, 우
		int index = 0;
		int targetTime = commands[index].time;
		while (true) {
			// targetTime 시간 후, 회전
			if (answer == targetTime) {
				direction = turnSnake(direction, commands[index].direction);
				if (index + 1 < commands.length) {
					index++;
					targetTime = commands[index].time;
				}
			}

			// 뱀 이동
			answer++;
			Node head = snake.peekLast();
			int nx = head.x + dx[direction];
			int ny = head.y + dy[direction];

			// 뱀이거나 지도를 나가면 종료
			if (!isRange(nx, ny) || checkSnake[nx][ny]) {
				break;
			}

			checkSnake[nx][ny] = true;
			snake.addLast(new Node(nx, ny));
			if (map[nx][ny] == 1) {    // 뱀이 사과를 먹을 경우
				map[nx][ny] = 0;
			} else {	// 뱀이 사과를 못먹을 경우, 꼬리 한 칸 이동
				Node node = snake.pollFirst();
				checkSnake[node.x][node.y] = false;
			}
		}
		return answer;
	}

	private static int turnSnake(int direction, char turnFlag) {
		if (turnFlag == 'L') {
			if (direction == 0) return 2;
			if (direction == 1) return 3;
			if (direction == 2) return 1;
			if (direction == 3) return 0;
		} else {
			if (direction == 0) return 3;
			if (direction == 1) return 2;
			if (direction == 2) return 0;
			if (direction == 3) return 1;
		}
		return -1;
	}

	private static boolean isRange(int nx, int ny) {
		return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = stoi(br.readLine());
		map = new int[N][N];
		checkSnake = new boolean[N][N];
		APPLE = stoi(br.readLine());
		for (int i = 0; i < APPLE; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken()) - 1;
			int y = stoi(st.nextToken()) - 1;
			map[x][y] = 1;
		}
		TURN = stoi(br.readLine());
		commands = new TimeAndDirection[TURN];
		for (int i = 0; i < TURN; i++) {
			st = new StringTokenizer(br.readLine());
			int time = stoi(st.nextToken());
			char direction = st.nextToken().charAt(0);
			commands[i] = new TimeAndDirection(time, direction);
		}
		snake.addLast(new Node(0, 0));
		checkSnake[0][0] = true;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
