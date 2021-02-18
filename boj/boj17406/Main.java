package boj.boj17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] map;
    static int[][] mapOrigin;
    static int[][] tmp;
    static boolean[] visited;
    static int[] indexArray;
    static int min = 5000;
    static Operation[] operations;
    static class Operation {
        int r, c, s;

        public Operation(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        map = new int[N][M];
        mapOrigin = new int[N][M];
        tmp = new int[N][M];
        operations = new Operation[K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                mapOrigin[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = stoi(st.nextToken()) - 1;
            int c = stoi(st.nextToken()) - 1;
            int s = stoi(st.nextToken());
            operations[i] = new Operation(r, c ,s);
        }
        visited = new boolean[K];
        indexArray = new int[K];
        permutation(0);
        System.out.println(min);

    }

    static void permutation(int cnt) {
        if (cnt == K) {
            map = copyOfMap();
            tmp = copyOfMap();
            solve();
            return;
        }

        for (int i = 0; i < K; i++) {
            visited[i] = true;
            indexArray[cnt] = i;
            permutation(cnt + 1);
            visited[i] = false;
        }
    }

    static void solve() {
        for (int idx : indexArray) {
            int x1 = operations[idx].r - operations[idx].s;
            int y1 = operations[idx].c - operations[idx].s;
            int x2 = operations[idx].r + operations[idx].s;
            int y2 = operations[idx].c + operations[idx].s;
            //rotation(x1, y1, x2, y2, operations[idx].s);
            min = Math.min(min, cal());
        }
    }

//    static void rotation(int x1, int y1, int x2, int y2, int s) {
//        int n = x2 - x1;
//        int m = y2 - y1;
//
//        for (int tc = 0; tc < s; tc++) {    // 시계 방향으로 회전
//            int x = x1 + tc;
//            int y = y1 + tc;
//            int ex = n - tc;
//            int ey = m - tc;
//            int gapX = ex - x;
//            int gapY = ey - y;
//            int tempNum = map[x][y];
//
//            // 위로
//            for (int i = x; i < ex; i++) {
//                map[i][y] = tmp[i + 1][y];
//            }
//
//            // 왼쪽으로
//            for (int j = y; j < ey; j++) {
//                map[x + gapX][j] = tmp[x + gapX][j + 1];
//            }
//
//            // 아래로
//            for (int i = ex; i > x + tc; i--) {
//                map[i][y + gapY] = tmp[i - 1][y + gapY];
//            }
//
//            // 오른쪽로
//            for (int j = ey; j > y + tc + 1; j--) {
//                map[x][j] = tmp[x][j - 1];
//            }
//            map[x][y + 1] = tempNum;
//
//        }
//
//        for (int i=0; i<N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//
//    }

    static int cal() {
        int res = 5000;
        for (int i = 0; i < N; i++) {
            int total = 0;
            for (int j = 0; j < M; j++) {
                total += map[i][j];
            }
            res = Math.min(res, total);
        }
        return res;
    }

    static int[][] copyOfMap() {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = mapOrigin[i][j];
            }
        }
        return tmp;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
