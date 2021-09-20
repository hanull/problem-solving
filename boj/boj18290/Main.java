package boj.boj18290;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int answer = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        go(0, 0);
        System.out.println(answer);
    }


    private static void go(int count, int total) {
        if (count == K) {
            answer = Math.max(answer, total);
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isPossible(i, j)) continue;
                if (visited[i][j]) continue;
                visited[i][j] = true;
                go(count + 1, total + map[i][j]);
                visited[i][j] = false;
            }
        }
    }

    private static boolean isPossible(int i, int j) {
        for (int d = 0; d < 4; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];
            if (!isRange(nx, ny)) continue;
            if (visited[nx][ny]) return false;
        }
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= M ? false : true;
    }
}
