package boj.boj13424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = stoi(st.nextToken());
            int M = stoi(st.nextToken());
            int[][] map = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                Arrays.fill(map[i], MAX);
                map[i][i] = 0;
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = stoi(st.nextToken());
                int to = stoi(st.nextToken());
                int weight = stoi(st.nextToken());
                map[from][to] = weight;
                map[to][from] = weight;
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (map[i][j] > map[i][k] + map[k][j]) {
                            map[i][j] = map[i][k] + map[k][j];
                        }
                    }
                }
            }

            int K = stoi(br.readLine());
            int[] friends = new int[K];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                friends[i] = stoi(st.nextToken());
            }

            int totalMin = Integer.MAX_VALUE;
            int answer = 0;
            for (int i = 1; i <= N; i++) {
                int total = 0;
                for (int k = 0; k < K; k++) {
                    int vertex = friends[k];
                    total += map[i][vertex];
                }
                if (total < totalMin) {
                    answer = i;
                    totalMin = total;
                }
            }
            System.out.println(answer);
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }


}
