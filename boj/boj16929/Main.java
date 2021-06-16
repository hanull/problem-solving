package boj.boj16929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1 ,1};
    static boolean[][] visited;
    static int[] alphabet = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                alphabet[map[i][j] - 'A']++;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (alphabet[map[i][j] - 'A'] >= 4) {
                    visited = new boolean[N][M];
                    dfs(i, j, i, j, 0, 1);
                }
            }
        }
        System.out.println("No");
    }

    private static void dfs(int x, int y, int goalX, int goalY, int direction, int count) {
        if (x == goalX && y == goalY && count >= 4) {
            System.out.println("Yes");
            System.exit(0);
        }
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!isRange(nx, ny)) continue;
            if (visited[nx][ny]) continue;
            if (map[nx][ny] != map[goalX][goalY]) continue;
            visited[nx][ny] = true;
            dfs(nx, ny, goalX, goalY, d, d == direction ? count : count + 1);
            visited[nx][ny] = false;
        }

    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= M ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
