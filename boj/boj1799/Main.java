package boj.boj1799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
5
1 1 0 1 1
0 1 0 0 1
1 0 1 0 1
1 0 0 0 0
1 0 1 1 1

answer : 8
 */
public class Main {

    static int N;
    static int[][] map;
    static int evenMax, oddMax;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        dfs(0, 0, 0, 0);
        dfs(0, 1, 0, 1);
        System.out.println(evenMax + oddMax);
    }

    private static void dfs(int x, int y, int total, int flag) {
        if (y >= N) {
            if (flag == 0) {
                y = (x + 1) % 2 == 0 ? 0 : 1;
            } else {
                y = (x + 1) % 2 == 0 ? 1 : 0;
            }
            dfs(x + 1, y, total, flag);
            return;
        }
        if (x >= N) {
            if (flag == 0) evenMax = Math.max(evenMax, total);
            else oddMax = Math.max(oddMax, total);
            return;
        }
        if (map[x][y] == 0 || map[x][y] == 2) {
            dfs(x, y + 2, total, flag);
            return;
        }
        if (!isPossible(x, y)) {
            dfs(x, y + 2, total, flag);
            return;
        }
        map[x][y] = 2;
        dfs(x, y + 2, total + 1, flag);
        map[x][y] = 1;
        dfs(x, y + 2, total, flag);
    }

    private static boolean isPossible(int x, int y) {
        int nx = x - 1;
        int ny = y - 1;
        while (nx >= 0 && ny >= 0) {
            if (map[nx--][ny--] == 2) return false;
        }
        nx = x - 1;
        ny = y + 1;
        while (nx >= 0 && ny < N) {
            if (map[nx--][ny++] == 2) return false;
        }
        nx = x + 1;
        ny = y - 1;
        while (nx < N && ny >= 0) {
            if (map[nx++][ny--] == 2) return false;
        }
        nx = x + 1;
        ny = y + 1;
        while (nx < N && ny < N) {
            if (map[nx++][ny++] == 2) return false;
        }
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
