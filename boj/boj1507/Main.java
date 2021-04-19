package boj.boj1507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map, originMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        originMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
                originMap[i][j] = map[i][j];
            }
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == k || k == j) continue;

                    if (originMap[i][j] > map[i][k] + map[k][j]) {
                        System.out.println(-1);
                        return;
                    }
                    if (originMap[i][j] == map[i][k] + map[k][j]) {
                        originMap[i][j] = 0;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += originMap[i][j];
            }
        }
        System.out.println(answer / 2);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
