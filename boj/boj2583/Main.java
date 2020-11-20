package boj.boj2583;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int M, N, K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int total = 0;
    static ArrayList<Integer> res = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        map = new int[M][N];
        visited = new boolean[M][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y1 = stoi(st.nextToken());
            int x1 = stoi(st.nextToken());
            int y2 = stoi(st.nextToken());
            int x2 = stoi(st.nextToken());
            checkRectangle(y1, x1, y2, x2);
        }
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    total = 1;
                    cnt++;
                    bfs(i, j);
                    res.add(total);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cnt + "\n");
        Collections.sort(res);
        for (int size : res) {
            sb.append(size + " ");
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    private static void bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        visited[x][y] = true;
        while (!q.isEmpty()) {
            Pair tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny] || map[nx][ny] == 1) continue;
                visited[nx][ny] = true;
                q.add(new Pair(nx, ny));
                total++;
            }
        }
    }

    private static boolean isRange(int x, int y) {
        if (x<0 || x>=M || y<0 || y>=N) return false;
        return true;
    }

    private static void checkRectangle(int y1, int x1, int y2, int x2) {
        for (int i = M - x2; i < M - x1; i++) {
            for (int j = y1; j <= y2 - 1; j++) {
                map[i][j] = 1;
            }
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}