package boj.boj2422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static int N, M;
    static boolean[][] badComb;
    static int totalCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        badComb = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            badComb[a][b] = true;
            badComb[b][a] = true;
        }

        for (int i = 1; i <= N - 2; i++) {
            for (int j = i + 1; j <= N - 1; j++) {
                if (badComb[i][j]) continue;
                for (int k = j + 1; k <= N; k++) {
                    if (badComb[i][k] || badComb[k][j]) continue;
                    totalCount++;
                }
            }
        }
        System.out.println(totalCount);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
