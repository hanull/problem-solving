package boj.boj2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static final int MAX_DISTANCE = 1000000;
    static int min = MAX_DISTANCE;
    static class Node {
        int x, y, dist, broken;

        public Node(int x, int y, int dist, int broken) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broken = broken;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        move();
        System.out.println(min == MAX_DISTANCE ? -1 : min);

    }

    static void move() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0));
        visited [0][0][0] = true;

        while (!q.isEmpty()) {
            Node tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int dist = tmp.dist;
            int broken = tmp.broken;

            if (tx == N - 1 && ty == M - 1) {
                min = Math.min(min, tmp.dist);
            }

            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny][broken]) continue;
                if (map[nx][ny] == 0) {
                    visited[nx][ny][broken] = true;
                    q.add(new Node(nx, ny, dist + 1, broken));
                } else {
                    if (broken == 0) {
                        visited[nx][ny][1] = true;
                        q.add(new Node(nx, ny, dist + 1, broken + 1));
                    }
                }
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
