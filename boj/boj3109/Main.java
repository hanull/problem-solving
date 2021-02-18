package boj.boj3109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static boolean[][] isChecked;
    static int[] dx = {-1, 0, 1}; // 오른쪽 위, 오른쪽, 오른쪽 아래
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        map = new char[R][C];
        isChecked = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            stealPipe(i, 0);
        }
        System.out.println(max);
    }

    static boolean stealPipe(int x, int y) {
        if (y == C - 1) {
            max++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + 1;
            if (!isRange(nx, ny) || map[nx][ny] == 'x') continue;
            if (isChecked[nx][ny]) continue;
            isChecked[nx][ny] = true;
            if (stealPipe(nx, ny)) return true;
        }
        return false;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= R || y < 0 || y >= C ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
