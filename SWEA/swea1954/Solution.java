package SWEA.swea1954;

import java.util.Scanner;

public class Solution {

    static int T, N;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            map = new int[N][N];

            int num = 1;
            int x = 0;
            int y = 0;
            int direction = 0;
            while (num < N * N) {
                while (true) {
                    if (!isRange(x, y) || map[x][y] > 0) break;
                    map[x][y] = num++;
                    x += dx[direction];
                    y += dy[direction];
                }
                x -= dx[direction];
                y -= dy[direction];
                direction = turn(direction);
                x += dx[direction];
                y += dy[direction];
            }
            map[x][y] = N * N;
            print(tc);
        }
    }

    static void print(int tc) {
        System.out.println("#" + tc);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int turn(int d) {
        if (d == 3) return 0;
        return d + 1;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N ? false : true;
    }
}
