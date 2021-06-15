package boj.boj1405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 30;
    static boolean[][] visited = new boolean[MAX][MAX];
    static int N;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static double[] percent = new double[4];
    static double answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        for (int i = 0; i < 4; i++) {
            percent[i] = stoi(st.nextToken()) * 0.01;
        }
        visited[14][14] = true;
        dfs(0, 1, 14, 14);
        System.out.println(answer);
    }

    private static void dfs(int count, double total, int x, int y) {
        if (count == N) {
            answer += total;
            return;
        }
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (visited[nx][ny]) continue;
            visited[nx][ny] = true;
            dfs(count + 1, total * percent[d], nx, ny);
            visited[nx][ny] = false;
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
