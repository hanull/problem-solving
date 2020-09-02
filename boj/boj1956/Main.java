package boj.boj1956;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int max = 400 * 10000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V, E;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        int[][] dist = new int[V + 1][V + 1];

        init(dist, st);
        floyd_warshall(dist);
        System.out.println(getTotalDistance(dist));
    }

    private static int getTotalDistance(int[][] dist) {
        int min = max;
        for (int i = 1; i <= V; i++) {
            min = Math.min(dist[i][i], min);
        }
        return min == max ? -1 : min;
    }

    private static void floyd_warshall(int[][] dist) {
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

    }

    private static void init(int[][] dist, StringTokenizer st) throws IOException {
        for (int i = 1; i <= V; i++) {
            Arrays.fill(dist[i], max);
        }
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int d = stoi(st.nextToken());
            dist[from][to] = Math.min(dist[from][to], d);
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
