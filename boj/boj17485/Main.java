package boj.boj17485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dx = {1, 1, 1};
    static int[] dy = {-1, 0, 1};
    static class Node {
        int x, y, total, direction;

        public Node(int x, int y, int total, int direction) {
            this.x = x;
            this.y = y;
            this.total = total;
            this.direction = direction;
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
        int answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        int answer = N * M * 100;
        PriorityQueue<Node> q = new PriorityQueue<>((Comparator.comparingInt(o -> o.total)));
        boolean[][][] visited = new boolean[N][M][4];
        for (int i = 0; i < M; i++) {
            q.add(new Node(0, i, map[0][i], 4));
        }
        while (!q.isEmpty()) {
            Node node = q.poll();
            int tx = node.x;
            int ty = node.y;
            int total = node.total;
            int direction = node.direction;
            if (tx == N - 1) {
                answer = Math.min(answer, total);
                break;
            }
            for (int d = 0; d < 3; d++) {
                if (d == direction) continue;
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (ny < 0 || ny >= M) continue;
                if (visited[nx][ny][d]) continue;
                visited[nx][ny][d] = true;
                q.add(new Node(nx, ny, total + map[nx][ny], d));
            }
        }
        return answer;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
