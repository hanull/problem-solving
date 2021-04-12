package SWEA.보급로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {

    static int N;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static class Node {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = stoi(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                char[] input = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    map[i][j] = input[j] - '0';
                }
            }
            result.append("#").append(tc).append(" ").append(move()).append("\n");
        }
        System.out.print(result);
    }

    static int move() {
        int result = Integer.MAX_VALUE;
        PriorityQueue<Node> deque = new PriorityQueue<>(((o1, o2) -> o1.dist - o2.dist));
        boolean[][] visited = new boolean[N][N];
        deque.add(new Node(0, 0, 0));
        visited[0][0] = true;

        while (!deque.isEmpty()) {
            Node tmp = deque.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int dist = tmp.dist;
            if (tx == N - 1 && ty == N - 1) {
                result = Math.min(dist, result);
            }
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                deque.add(new Node(nx, ny, dist + map[nx][ny]));
            }
        }


        return result;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
