package boj.boj16956;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static ArrayList<Wolf> wolves = new ArrayList<>();

    static class Wolf {
        int x, y;

        public Wolf(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {   // 빈 칸 -> 울타리
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == '.') map[i][j] = 'D';
                else if (map[i][j] == 'W') wolves.add(new Wolf(i, j));
            }
        }

        boolean flag = false;
        for (Wolf wolf : wolves) {
            int x = wolf.x;
            int y = wolf.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == 'S') {
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }

        if (flag) {
            System.out.println(0);
        } else {
            System.out.println(1);
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }

    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= R || y < 0 || y >= C ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
