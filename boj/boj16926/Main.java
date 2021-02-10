package boj.boj16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, T;
    static int[][] map;
    static int[][] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        int R = stoi(st.nextToken());
        map = new int[N][M];
        tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        T = Math.min(N, M);
        T /= 2;
        while (R-- > 0) {
            rotation();
        }
        print();

    }

    static void rotation() {
        copyOfMap();

        for (int tc = 0; tc < T; tc++) {
            // 아래로
            for (int i = tc; i < N - 1 - tc; i++) {
                map[i + 1][tc] = tmp[i][tc];
            }

            // 오른쪽로
            for (int j = tc; j < M - 1 - tc; j++) {
                map[N - 1 - tc][j + 1] = tmp[N - 1 - tc][j];
            }

            // 위로
            for (int i = N - 1 - tc; i >= tc + 1; i--) {
                map[i - 1][M - 1 - tc] = tmp[i][M - 1 - tc];
            }

            // 왼쪽으로
            for (int j = M - 1 - tc; j >= tc + 1; j--) {
                map[tc][j - 1] = tmp[tc][j];
            }

        }
    }

    static void copyOfMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = map[i][j];
            }
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
