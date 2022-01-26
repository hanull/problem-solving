package Programmers.거리두기확인하기;

import java.util.*;

class Solution {

	static class Node {
		int x, y, dist;
		public Node (int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public int[] solution(String[][] places) {
		int[] answer = new int[places.length];
		for (int i=0; i<places.length; i++) {
			if (isValid(places[i])) {
				answer[i] = 1;
			} else {
				answer[i] = 0;
			}
		}
		return answer;
	}

	private boolean isValid(String[] place) {
		char[][] map = new char[5][5];
		boolean[][] visited = new boolean[5][5];
		for (int i=0; i<place.length; i++) {
			map[i] = place[i].toCharArray();
		}
		for (int i=0; i<place.length; i++) {
			for (int j=0; j<5; j++) {
				if (map[i][j] == 'P') {
					if (!isValidPlace(map, i, j, visited)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private boolean isValidPlace(char[][] map, int x, int y, boolean[][] visited) {
		Queue<Node> q = new LinkedList<>();
		// boolean[][] visited = new boolean[5][5];
		q.add(new Node(x, y, 0));
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Node node = q.poll();
			int tx = node.x;
			int ty = node.y;
			int dist = node.dist;
			if (dist > 0 && map[tx][ty] == 'P') {
				return false;
			}
			for (int d=0;d<4;d++) {
				int nx = tx + dx[d];
				int ny = ty + dy[d];
				if (!isRange(nx, ny)) {
					continue;
				}
				if (map[nx][ny] == 'X') {
					continue;
				}
				if (visited[nx][ny]) {
					continue;
				}
				visited[nx][ny] = true;
				if (dist < 2) {
					q.add(new Node(nx, ny, dist + 1));
				}
			}
		}
		return true;
	}

	private boolean isRange(int nx, int ny) {
		return nx >= 0 && nx < 5 && ny >= 0 && ny < 5;
	}
}