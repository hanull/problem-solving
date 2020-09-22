package boj.boj15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class CCTV {
    int no, x, y;

    public CCTV(int no, int x, int y) {
        this.no = no;
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N, M;
    static ArrayList<CCTV> list = new ArrayList<>();
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        int[][] map = new int[N][M];
        min = N * M;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    list.add(new CCTV(map[i][j], i, j));
                }
            }
        }
        dfs(0, list.size(), map);
        System.out.println(min);
    }

    private static void dfs(int idx, int n, int[][] map) {
        if (idx == n) {
            min = Integer.min(min, getCnt(map));
            return ;
        }
        int no = list.get(idx).no;
        int x = list.get(idx).x;
        int y = list.get(idx).y;
        switch (no) {
            case 5:
                // 상, 하, 좌, 우
                checkingMap(1, x, y, map);
                checkingMap(2, x, y, map);
                checkingMap(3, x, y, map);
                checkingMap(4, x, y, map);
                dfs(idx + 1, n, map);
                break;
            case 4: // 4가지 종류
                int[][] tmp = arrayCopy(map);
                checkingMap(1, x, y, tmp);
                checkingMap(2, x, y, tmp);
                checkingMap(3, x, y, tmp);
                dfs(idx + 1, n, tmp);

                tmp = arrayCopy(map);
                checkingMap(2, x, y, tmp);
                checkingMap(3, x, y, tmp);
                checkingMap(4, x, y, tmp);
                dfs(idx + 1, n, tmp);

                tmp = arrayCopy(map);
                checkingMap(1, x, y, tmp);
                checkingMap(2, x, y, tmp);
                checkingMap(4, x, y, tmp);
                dfs(idx + 1, n, tmp);

                tmp = arrayCopy(map);
                checkingMap(1, x, y, tmp);
                checkingMap(3, x, y, tmp);
                checkingMap(4, x, y, tmp);
                dfs(idx + 1, n, tmp);
                break;
            case 3:
                tmp = arrayCopy(map);
                checkingMap(1, x, y, tmp);
                checkingMap(4, x, y, tmp);
                dfs(idx + 1, n, tmp);

                tmp = arrayCopy(map);
                checkingMap(4, x, y, tmp);
                checkingMap(2, x, y, tmp);
                dfs(idx + 1, n, tmp);

                tmp = arrayCopy(map);
                checkingMap(3, x, y, tmp);
                checkingMap(2, x, y, tmp);
                dfs(idx + 1, n, tmp);

                tmp = arrayCopy(map);
                checkingMap(1, x, y, tmp);
                checkingMap(3, x, y, tmp);
                dfs(idx + 1, n, tmp);
                break;
            case 2:
                tmp = arrayCopy(map);
                checkingMap(1, x, y, tmp);
                checkingMap(2, x, y, tmp);
                dfs(idx + 1, n, tmp);

                tmp = arrayCopy(map);
                checkingMap(3, x, y, tmp);
                checkingMap(4, x, y, tmp);
                dfs(idx + 1, n, tmp);
                break;
            case 1:
                tmp = arrayCopy(map);
                checkingMap(1, x, y, tmp);
                dfs(idx + 1, n, tmp);

                tmp = arrayCopy(map);
                checkingMap(2, x, y, tmp);
                dfs(idx + 1, n, tmp);

                tmp = arrayCopy(map);
                checkingMap(3, x, y, tmp);
                dfs(idx + 1, n, tmp);

                tmp = arrayCopy(map);
                checkingMap(4, x, y, tmp);
                dfs(idx + 1, n, tmp);
                break;
        }
    }

    private static void checkingMap(int direction, int x, int y, int[][] map) {
        switch (direction) {
            case 1:         //  상
                for (int i = x - 1; i >= 0; i--) {
                    if (map[i][y] == 6) break;
                    map[i][y] = -1;
                }
                break;
            case 2:         // 하
                for (int i = x + 1; i < N; i++) {
                    if (map[i][y] == 6) break;
                    map[i][y] = -1;
                }
                break;
            case 3:         // 좌
                for (int i = y - 1; i >= 0; i--) {
                    if (map[x][i] == 6) break;
                    map[x][i] = -1;
                }
                break;
            case 4:         // 우
                for (int i = y + 1; i < M; i++) {
                    if (map[x][i] == 6) break;
                    map[x][i] = -1;
                }
                break;
        }
    }


    private static int[][] arrayCopy(int[][] map) {
        int[][] res = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                res[i][j] = map[i][j];
            }
        }
        return res;
    }

    private static int getCnt(int[][] map) {
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    res++;
                }
            }
        }
        return res;
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
