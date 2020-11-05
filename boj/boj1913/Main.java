package boj.boj1913;

import java.util.Scanner;

public class Main {

    static int[][] map;
    static int N, searchPoint;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        searchPoint = sc.nextInt();
        map = new int[N][N];
        int goalX = N/2;
        int goalY = N/2;
        System.out.println(solve(goalX, goalY));
    }

    private static String solve(int goalX, int goalY) {
        int x = 0;
        int y = 0;
        int d = 0;
        int num = N * N;
        int resX = 0;
        int resY = 0;
        while (true) {
            map[x][y] = num;
            if (x==goalX && y==goalY) break;
            if (map[x][y] == searchPoint) {
                resX = x;
                resY = y;
            }
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != 0) {
                d = turn(d);
                continue;
            } else {
                x = nx;
                y = ny;
                num--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        sb.append((resX + 1) + " " + (resY + 1));
        return sb.toString();
    }

    private static int turn(int d) {
        int res = 0;
        switch (d) {
            case 0:
                res = 1;
                break;
            case 1:
                res = 2;
                break;
            case 2:
                res = 3;
                break;
            case 3:
                res = 0;
                break;
        }
        return res;
    }
}
