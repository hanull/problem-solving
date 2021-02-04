package boj.boj1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int T, N, M, K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Cabbage[] cabbages;

    static class Cabbage {
        int x, y;

        public Cabbage(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = stoi(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = stoi(st.nextToken());
            N = stoi(st.nextToken());
            K = stoi(st.nextToken());
            map = new int[N][M];
            visited = new boolean[N][M];
            cabbages = new Cabbage[K];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int y = stoi(st.nextToken());
                int x = stoi(st.nextToken());
                map[x][y] = 1;
                cabbages[i] = new Cabbage(x, y);
            }

            int cnt = 0;
            for (int i = 0; i < K; i++) {
                int x = cabbages[i].x;
                int y = cabbages[i].y;
                if (visited[x][y]) continue;
                searchBugs(x, y);
                cnt++;
            }

            System.out.println(cnt);
        }

    }

    static void searchBugs(int x, int y) {
        Queue<Cabbage> q = new LinkedList<>();
        q.add(new Cabbage(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Cabbage tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == 0) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new Cabbage(nx, ny));
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
