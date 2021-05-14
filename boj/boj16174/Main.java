package boj.boj16174;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static boolean[][] visited;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        System.out.println(isPossible() ? "HaruHaru" : "Hing");
    }

    private static boolean isPossible() {
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(0, 0));
        visited[0][0] = true;

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            int size = map[tx][ty];
            if (tx == N-1 && ty == N-1) return true;
            for (int d = 0; d < 2; d++) {
                int nx = tx + (dx[d] * size);
                int ny = ty + (dy[d] * size);
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                deque.add(new Node(nx, ny));
            }
        }
        return false;
    }

    private static boolean isRange(int nx, int ny) {
        return nx >= N || ny >= N ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
