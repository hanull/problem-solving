package jungol.해밀턴순환회로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int min = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[] visited;

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
        visited[0] = true;
        dfs(1, 0, 0);
        System.out.println(min);
    }

    static void dfs(int cnt, int index, int total) {
        if (total >= min) return;
        if (cnt == N) {
            if (map[index][0] > 0 ) min = Math.min(min, total + map[index][0]);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (index == i) continue;
            if (map[index][i] == 0) continue;
            if (visited[i]) continue;
            visited[i] = true;
            dfs(cnt + 1, i, total + map[index][i]);
            visited[i] = false;
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
