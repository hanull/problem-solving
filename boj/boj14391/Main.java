package boj.boj14391;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int maxValue;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(input[j]);
            }
        }
        dfs(0, 0);
        System.out.println(maxValue);
    }

    private static void dfs(int x, int y) {
        if (x == N) {
            maxValue = Math.max(maxValue, calculate());
            return;
        }
        if (y == M) {
            dfs(x + 1,  0);
            return;
        }
        visited[x][y] = true;
        dfs(x, y + 1);
        visited[x][y] = false;
        dfs(x, y + 1);
    }

    private static int calculate() {
        int total = 0;

        // 가로 (true)
        for (int i = 0; i < N; i++) {
            int number = 0;
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    number = number * 10 + map[i][j];
                } else {
                    total += number;
                    number = 0;
                }
            }
            total += number;
        }

        // 세로 (false)
        for (int j = 0; j < M; j++) {
            int number = 0;
            for (int i = 0; i < N; i++) {
                if (!visited[i][j]) {
                    number = number * 10 + map[i][j];
                } else {
                    total += number;
                    number = 0;
                }
            }
            total += number;
        }
        return total;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
