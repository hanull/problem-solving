package boj.boj17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {0, 1, 1}; // 가로, 세로, 대각선 아래
    static int[] dy = {1, 0, 1};
    static int[][] nextDirection = {{-1, 0, 2}, {-1, 1, 2}, {0, 1, 2}}; // 가로, 세로, 대각선

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        dfs(0, 1, 0);
        System.out.println(answer);
    }

    private static void dfs(int x, int y, int d) {
        if (x == N - 1 && y == N - 1) {
            answer++;
            return;
        }
        for (int next : nextDirection[d]) {
            if (next == -1) continue;
            int nx = x + dx[next];
            int ny = y + dy[next];
            if (!isRange(nx, ny)) continue;
            if (!isPossibleToMove(nx, ny, next)) continue;
            if (visited[nx][ny][next]) continue;
            visited[nx][ny][next] = true;
            dfs(nx, ny, next);
            visited[nx][ny][next] = false;
        }
    }

    private static boolean isPossibleToMove(int nx, int ny, int d) {
        if (d == 2) {
            for (int i = nx - 1; i <= nx; i++) {
                for (int j = ny - 1; j <= ny; j++) {
                    if (map[i][j] == 1) return false;
                }
            }
        } else {
            if (map[nx][ny] == 1) return false;
        }
        return true;
    }

    private static boolean isRange(int nx, int ny) {
        return nx >= N || ny >= N ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
