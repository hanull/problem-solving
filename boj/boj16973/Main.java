package boj.boj16973;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H, W,  startX, startY, goalX, goalY;
    static BufferedReader br;
    static int[][] map, count;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};
    static class Node {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(bfs());
    }

    private static int bfs() {
        int answer = -1;
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new Node(startX, startY, 0));
        visited[startX][startY] = true;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int tx = node.x;
            int ty = node.y;
            int dist = node.dist;
            if (tx == goalX && ty == goalY) {
                answer = dist;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == 1) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new Node(nx, ny, dist + 1));
            }
        }
        return answer;
    }

    private static boolean isRange(int x, int y) {
        return x < 0 || x > N - H || y < 0 || y > M - W || count[x][y] != 0 ? false : true;
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        count = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        H = stoi(st.nextToken());
        W = stoi(st.nextToken());
        startX = stoi(st.nextToken()) - 1;
        startY = stoi(st.nextToken()) - 1;
        goalX = stoi(st.nextToken()) - 1;
        goalY = stoi(st.nextToken()) - 1;
        for (int i = 0; i <= N - H; i++) {
            for (int j = 0; j <= M - W; j++) {
                count[i][j] = getTotal(i, j);
            }
        }
    }

    private static int getTotal(int x, int y) {
        int total = 0;
        for (int i = x; i < x + H; i++) {
            for (int j = y; j < y + W; j++) {
                total += map[i][j];
            }
        }
        return total;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
