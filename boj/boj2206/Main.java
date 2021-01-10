package boj.boj2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static final int max = 1000000;
    static int dist = max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(input[j]);
            }
        }

        bfs();
        dist = dist == max ? -1 : dist;
        System.out.println(dist);
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int cnt = tmp.cnt;
            int broken = tmp.broken;

            if (tx == N - 1 && ty == M - 1) {
                dist = Math.min(dist, cnt);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny][broken]) continue;
                if (map[nx][ny] == 0) {
                    visited[nx][ny][broken] = true;
                    q.add(new Node(nx, ny, cnt + 1, broken));
                } else if (broken == 0){
                    visited[nx][ny][1] = true;
                    q.add(new Node(nx, ny, cnt + 1, 1));
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Node {
    int x, y, cnt, broken;

    public Node(int x, int y, int cnt, int broken) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.broken = broken;
    }
}
