package boj.boj14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[] visited;
    static int N;
    static int min = 1000;
    static int teamS;
    static int teamL;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        map = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(min);
    }

    private static void dfs(int idx, int cnt) {
        if (cnt == N / 2) {
            getTotal();
            int minus = Math.abs(teamL - teamS);
            if (min > minus) min = minus;
            return;
        }
        for (int i = idx; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(i + 1,cnt + 1);
            visited[i] = false;
        }
    }

    private static void getTotal() {
        teamL = teamS = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i==j) continue;
                if (visited[i] && visited[j]) {
                    teamS += map[i][j];
                } else if (!visited[i] && !visited[j]) {
                    teamL += map[i][j];
                }
            }
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
