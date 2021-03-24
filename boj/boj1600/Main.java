package boj.boj1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
1
5 5
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 1 1
0 0 0 1 0
 */
public class Main {

    static int K, W, H;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0, -2, -2, -1, -1, 2, 2, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -2, 2, -1, 1, -2, 2};
    static int finalMinDistance = Integer.MAX_VALUE;
    static class Node {
        int x, y, dist, jumpCount;

        public Node(int x, int y, int dist, int jumpCount) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.jumpCount = jumpCount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = stoi(st.nextToken());
        H = stoi(st.nextToken());
        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        move();
        System.out.println(finalMinDistance == Integer.MAX_VALUE ? -1 : finalMinDistance);
    }

    static void move() {
        Deque<Node> deque = new ArrayDeque<>();
        boolean[][][] visited = new boolean[H][W][K + 1];
        deque.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            int dist = tmp.dist;
            int jumpCount = tmp.jumpCount;

            if (tx == H - 1 && ty == W - 1) {
                finalMinDistance = dist;
                break;
            }

            int endPoint = jumpCount < K ? 12 : 4;
            for (int i = 0; i < endPoint; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                int isJump = i >= 4 ? 1 : 0;
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny][jumpCount + isJump]) continue;
                if (map[nx][ny] == 1) continue;
                visited[nx][ny][jumpCount + isJump] = true;
                deque.add(new Node(nx, ny, dist + 1, i >= 4 ? jumpCount + 1 : jumpCount));
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= H || y < 0 || y >= W ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
