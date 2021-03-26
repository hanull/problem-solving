package boj.boj17836;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, M, endTime;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y, dist;
        boolean haveSword;

        public Node(int x, int y, int dist, boolean haveSword) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.haveSword = haveSword;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        endTime = stoi(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        int result = findThePrincess();
        System.out.println(result == -1 ? "Fail" : result);
    }

    static int findThePrincess() {
        int time = 0;
        Deque<Node> deque = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2];
        deque.add(new Node(0, 0, 0, false));
        visited[0][0][0] = true;

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            int dist = tmp.dist;
            boolean haveSword = tmp.haveSword;
            if (map[tx][ty] == 2) haveSword = true;

            if (tx == N - 1 && ty == M - 1) {
                time = dist;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (!haveSword && map[nx][ny] == 1) continue;
                if (visited[nx][ny][haveSword ? 1 : 0]) continue;
                visited[nx][ny][haveSword ? 1 : 0] = true;
                deque.add(new Node(nx, ny, dist + 1, haveSword));
            }
        }
        return time == 0 || time > endTime ? -1 : time;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
