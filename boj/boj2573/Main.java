package boj.boj2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
5 5
0 0 0 0 0
0 2 2 2 0
0 2 2 2 0
0 2 2 2 0
0 0 0 0 0
 */
public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
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
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        int time = 0;
        boolean flag = false;
        while (true) {
            // 분리된 빙산 수 카운트
            int icebergCount = 0;
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        searchIceberg(i, j);
                        icebergCount++;
                    }
                }
            }

            if (icebergCount >= 2) {
                flag = true;
                break;
            }
            if (icebergCount == 0) break;

            // 바닷물에 인접한 빙산의 높이 -1
            Deque<Node> seaWater = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        for (int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if (!isRange(nx, ny)) continue;
                            if (map[nx][ny] == 0) continue;
                            seaWater.add(new Node(nx, ny));
                        }
                    }
                }
            }

            while (!seaWater.isEmpty()) {
                Node tmp = seaWater.pollFirst();
                int x = tmp.x;
                int y = tmp.y;
                if (map[x][y] > 0) map[x][y] -= 1;
            }
            time++;
        }
        System.out.println(flag ? time : 0);
    }

    static void searchIceberg(int x, int y) {
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(x, y));
        visited[x][y] = true;

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int d = 0; d < 4; d++) {
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

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
