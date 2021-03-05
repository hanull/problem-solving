package boj.boj16918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int R, C, N;
    static char[][] map;
    static int[][] timeMap;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Deque<Bomb> bombList = new ArrayDeque<>();

    static class Bomb {
        int x, y, time;

        public Bomb(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        N = stoi(st.nextToken());
        map = new char[R][C];
        timeMap = new int[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'O') {
                    bombList.add(new Bomb(i, j, 0));
                    timeMap[i][j] = 0;
                }
            }
        }

        for (int time = 2; time <= N; time++) {
            // 빈칸 채우기
            if (time % 2 == 0) {
                fillInTheBlanks(time);
            }
            // 폭발
             else {
                explode(time);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void explode(int time) {
        while (!bombList.isEmpty()) {
            if (bombList.peekFirst().time + 3 - time != 0) break;
            Bomb bomb = bombList.pollFirst();
            int x = bomb.x;
            int y = bomb.y;
            if (bomb.time != timeMap[x][y]) continue;
            map[x][y] = '.';
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == '.') continue;
                map[nx][ny] = '.';
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= R || y < 0 || y >= C ? false : true;
    }

    static void fillInTheBlanks(int time) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.') {
                    bombList.addLast(new Bomb(i, j, time));
                    map[i][j] = 'O';
                    timeMap[i][j] = time;
                }
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
