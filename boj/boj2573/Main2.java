package boj.boj2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1 , 1};
    static int N, M;
    static int[][] map;

    static class Node {
        int x, y;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solve());
    }

    private static int solve() {
        int answer = 0;

        while (true) {
            // 0인 공간 찾기
            Queue<Node> q = findEmpty();

            // 다 녹았을 경우
            if (N * M == q.size()) {
                return 0;
            }

            // 녹이기
            melt(q);

            answer++;

            // 분리 됐는지 확인
            if (isSeparate()) {
                return answer;
            }
        }
    }

    private static Queue<Node> findEmpty() {
        Queue<Node> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    q.add(new Node(i, j));
                }
            }
        }
        return q;
    }

    private static void melt(final Queue<Node> q) {
        while (!q.isEmpty()) {
            Node node = q.poll();
            int tx = node.x;
            int ty = node.y;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    continue;
                }
                map[nx][ny]--;
            }
        }
    }

    private static boolean isSeparate() {
        int count = 0;
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    count++;
                    bfs(i, j, visited);
                }
            }
        }
        return count > 1;
    }

    private static void bfs(final int x, final int y, final boolean[][] visited) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y));

        while (!q.isEmpty()) {
            Node node = q.poll();
            int tx = node.x;
            int ty = node.y;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new Node(nx, ny));
            }
        }
    }
}
