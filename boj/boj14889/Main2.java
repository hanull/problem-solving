package boj.boj14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static int N;
    static int[][] map;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(min);

    }

    static void dfs(int cnt, int start) {
        if (cnt == N / 2) {
            min = Math.min(min, calc());
            return;
        }
        for (int i = start; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(cnt + 1, i + 1);
            visited[i] = false;
        }
    }

    static int calc() {
        int teamA = 0;
        int teamB = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i] && visited[j]) {
                    teamA += map[i][j];
                } else if (!visited[i] && !visited[j]) {
                    teamB += map[i][j];
                }
            }
        }
        return Math.abs(teamA - teamB);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
