package boj.boj14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int max;
    static int[][][] tetromino = {
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
            {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
            {{0, 1}, {1, 1}, {2, 1}, {2, 0}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 0}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
            {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {0, 1}},
            {{0, 2}, {1, 0}, {1, 1}, {1, 2}},
            {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
            {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
            {{0, 1}, {1, 0}, {1, 1}, {2, 0}},
            {{0, 1}, {0, 2}, {1, 0}, {1, 1}},
            {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {1, 1}},
            {{0, 1}, {1, 0}, {1, 1}, {1, 2}},
            {{0, 1}, {1, 0}, {1, 1}, {2, 1}}
        };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                putTetromino(i, j);
            }
        }
        System.out.println(max);

    }

    static void putTetromino(int x, int y) {
        int total = 0;
        boolean flag = true;
        for (int i = 0; i < 19; i++) {
            total = 0;
            flag = true;
            for (int j = 0; j < 4; j++) {
                int nx = x + tetromino[i][j][0];
                int ny = y + tetromino[i][j][1];
                if (!isRange(nx, ny)) {
                    flag = false;
                    break;
                }
                total += map[nx][ny];
            }
            if (flag) max = Math.max(max, total);
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
