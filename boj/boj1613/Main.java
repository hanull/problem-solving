package boj.boj1613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken());
        boolean[][]  map = new boolean[N][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int before = stoi(st.nextToken()) - 1;
            int next = stoi(st.nextToken()) - 1;
            map[before][next] = true;
        }
        for (int d = 0; d < N; d++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][d] && map[d][j]) {
                        map[i][j] = true;
                    }
                }
            }
        }
        int S = stoi(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int before = stoi(st.nextToken()) - 1;
            int next = stoi(st.nextToken()) - 1;
            if (map[before][next]) answer.append(-1);
            else if (map[next][before]) answer.append(1);
            else answer.append(0);
            answer.append("\n");
        }
        System.out.print(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
