package boj.boj10159;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = stoi(br.readLine());
        int M = stoi(br.readLine());
        boolean[][] map = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            map[from][to] = true;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (!map[i][j] && map[i][k] && map[k][j]) {
                        map[i][j] = true;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int total = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (!map[i][j] && !map[j][i]) total++;
            }
            result.append(total).append("\n");
        }
        System.out.print(result);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
