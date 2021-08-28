package boj.boj15661;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] power;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        power = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                power[i][j] = stoi(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(min);
    }

    private static void dfs(int index) {
        if (index == N - 1) {
            if (isPossible()) {
                min = Math.min(min, calculate());
            }
            return;
        }

        // 선택하지 않을 경우
        visited[index] = false;
        dfs(index + 1);

        //  선택할 경우
        visited[index] = true;
        dfs(index + 1);

    }

    private static boolean isPossible() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) count++;
        }
        return count != 0 && count != N;
    }

    private static int calculate() {
        int totalA = 0, totalB = 0;

        for (int i = 0; i < N; i++) {
            if (!visited[i]) continue;
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if (!visited[j]) continue;
                totalA += power[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if (visited[j]) continue;
                totalB += power[i][j];
            }
        }
        return Math.abs(totalA - totalB);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
