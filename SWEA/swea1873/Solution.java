package SWEA.swea1873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T, H, W, N;
    static char[][] map;
    static char[] command;
    static char[] tankShape = {'^', 'v', '<', '>'};
    static int[] dx = {-1, 1, 0 , 0};
    static int[] dy = {0, 0, -1, 1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Tank tank;

    static class Tank {
        int x, y;
        int direction;

        public Tank(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public int getX() {
            return x;
        }
    }

    public static void main(String[] args) throws IOException {
        T = stoi(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            init();     // input
            control();
            print(tc);
        }
        br.close();
    }

    static void print(int tc) {
        map[tank.x][tank.y] = tankShape[tank.direction];
        System.out.print("#" + tc + " " );
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static void control() {
        for (char ch : command) {
            if (ch == 'S') {
                shoot(tank.x, tank.y, tank.direction);
            } else {
                tank.direction = turn(ch);
                move();
            }
        }
    }

    static void shoot(int x, int y, int d) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        while (isRange(nx, ny)) {
            if (map[nx][ny] == '#' || map[nx][ny] == '*') {     // 벽돌을 만날 경우, 땅은 평지로 변경 (.)
                if (map[nx][ny] == '*') map[nx][ny] = '.';
                break;
            }
            nx += dx[d];
            ny += dy[d];
        }
    }

    static int turn(char ch) {
        int res = 0;
        switch (ch) {
            case 'U':
                res = 0;
                break;
            case 'D':
                res = 1;
                break;
            case 'L':
                res = 2;
                break;
            case 'R':
                res = 3;
                break;
        }
        return res;
    }

    static void move() {
        // 이동할 곳이 평지일 경우, 이동
        int nx = tank.x + dx[tank.direction];
        int ny = tank.y + dy[tank.direction];
        if (isRange(nx, ny) && map[nx][ny] == '.') {
            tank.x = nx;
            tank.y = ny;
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= H || y < 0 || y >= W ? false : true;
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        H = stoi(st.nextToken());
        W = stoi(st.nextToken());
        map = new char[H][W];
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                if (map[i][j] == '^') {
                    tank = new Tank(i, j, 0);
                    map[i][j] = '.';
                    break;
                } else if (map[i][j] == 'v') {
                    tank = new Tank(i, j, 1);
                    map[i][j] = '.';
                    break;
                } else if (map[i][j] == '<') {
                    tank = new Tank(i, j, 2);
                    map[i][j] = '.';
                    break;
                } else if (map[i][j] == '>') {
                    tank = new Tank(i, j, 3);
                    map[i][j] = '.';
                    break;
                }
            }
        }
        N = stoi(br.readLine());
        command = br.readLine().toCharArray();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
