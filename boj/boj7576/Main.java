package boj.boj7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Node> q = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int tomatoCnt = 0;
    static int res = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] == 0) tomatoCnt++;
                if (map[i][j] == 1) q.add(new Node(i, j, 0));
            }
        }
        int cnt = bfs();
        if (tomatoCnt == cnt) {
            System.out.println(res);
        } else {
            System.out.println(-1);
        }
    }

    private static int bfs() {
        int cnt = 0;
        while (!q.isEmpty()) {
            Node tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int time = tmp.time;
            res = time;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == -1 || map[nx][ny] == 1) continue;
                visited[nx][ny] = true;
                q.add(new Node(nx, ny, time + 1));
                cnt++;
            }
        }
        return cnt;
    }

    private static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    public static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Node {
    int x, y, time;

    public Node(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}