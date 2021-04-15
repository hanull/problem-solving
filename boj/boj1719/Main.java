package boj.boj1719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 987654321;
    static int N, M;
    static int[][] cost;
    static int[][] startPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        cost = new int[N + 1][N + 1];
        startPoint = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(cost[i], MAX);
            cost[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            cost[from][to] = weight;
            cost[to][from] = weight;
            startPoint[from][to] = to;
            startPoint[to][from] = from;
        }


        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                        startPoint[i][j] = startPoint[i][k];
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i!=j) result.append(startPoint[i][j]).append(" ");
                else result.append("- ");
            }
            result.append("\n");
        }
        System.out.print(result);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
