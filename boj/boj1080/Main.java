package boj.boj1080;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] targetMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = chars[j] - '0';
            }
        }
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                targetMap[i][j] = chars[j] - '0';
            }
        }

        int answer = 0;
        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= M - 3; j++) {
                if (map[i][j] != targetMap[i][j]) {
                    turn(i, j, map);
                    answer++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != targetMap[i][j]) {
                    System.out.println(-1);
                    System.exit(0);
                }
            }
        }
        System.out.println(answer);
    }

    private static void turn(final int row, final int col, final int[][] map) {
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = 0;
                } else {
                    map[i][j] = 1;
                }
            }
        }
    }
}
