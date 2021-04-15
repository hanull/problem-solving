package boj.boj11909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static int N;
    static int[][] map, cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        for (int i = 1; i < N; i++) {
            cost[i][0] = cost[i - 1][0] + Math.max(0, map[i][0] - map[i - 1][0] + 1);
            cost[0][i] = cost[0][i - 1] + Math.max(0, map[0][i] - map[0][i - 1] + 1);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                cost[i][j] = Math.min(cost[i - 1][j] + Math.max(0, map[i][j] - map[i - 1][j] + 1), cost[i][j - 1] + Math.max(0, map[i][j] - map[i][j - 1] + 1));
            }
        }
        System.out.println(cost[N - 1][N - 1]);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
