package boj.boj14716;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static class Node {
        int x, y;

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
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    searchWord(i, j);
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    private static void searchWord(int x, int y) {
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(x, y));
        visited[x][y] = true;

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int d = 0; d < 8; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                deque.add(new Node(nx, ny));
            }
        }
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= M ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
