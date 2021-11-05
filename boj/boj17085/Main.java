package boj.boj17085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] result = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        dfs(0);
        System.out.println(answer);
    }

    private static void dfs(int count) {
        if (count == 2) {
            answer = Math.max(answer, result[0] * result[1]);
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '#' && !visited[i][j]) {
                    for (int len = 0; len <= 7; len++) {
                        if (!isPossible(i, j, len)) break;
                        put(i, j, len, true);
                        int size = len == 0 ? 1 : len * 4 + 1;
                        result[count] = size;
                        dfs(count + 1);
                        put(i, j, len, false);
                    }
                }
            }
        }
    }

    private static boolean isPossible(int x, int y, int size) {
        if (size == 0) return true;
        for (int d = 0; d < 4; d++) {
            int tx = x;
            int ty = y;
            for (int count = 0; count < size; count++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == '.') return false;
                tx = nx;
                ty = ny;
            }
        }
        return true;
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= M ? false : true;
    }

    private static void put(int x, int y, int len, boolean flag) {
        visited[x][y] = flag;
        for (int d = 0; d < 4; d++) {
            int tx = x;
            int ty = y;
            for (int count = 0; count < len; count++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                visited[nx][ny] = flag;
                tx = nx;
                ty = ny;
            }
        }
    }
}
