package boj.boj15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int answer = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        N = stoi(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map[j][i] = i;
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken()) - 1;
            int y = stoi(st.nextToken()) - 1;
            map[x][y] = y + 1;
            map[x][y + 1] = y;
        }
        dfs(0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void dfs(int index, int count) {
        if (count > 3) return;
        if (isPossible()) {
            answer = Math.min(answer, count);
            return;
        }
        for (int col = index; col < M - 1; col++) {
            for (int row = 0; row < N; row++) {
                if (map[row][col] != col || map[row][col + 1] != col + 1) continue;
                map[row][col] = col + 1;
                map[row][col + 1] = col;
                dfs(col, count + 1);
                map[row][col] = col;
                map[row][col + 1] = col + 1;
            }
        }
    }

    private static boolean isPossible() {
        for (int t = 0; t < M; t++) {
            int col = t;
            for (int row = 0; row < N; row++) {
                col = map[row][col];
            }
            if (col != t) return false;
        }
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
