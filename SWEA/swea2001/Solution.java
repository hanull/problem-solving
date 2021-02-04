package SWEA.swea2001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T, N, M;
    static int[][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int max;

    public static void main(String[] args) throws IOException {
        T = stoi(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            max = 0;
            for (int i = 0; i <= N - M; i++) {
                for (int j = 0; j <= N - M; j++) {
                    int cnt = kill(i, j);
                    max = Math.max(max, cnt);
                }
            }
            System.out.println("#" + tc + " " + max);
        }
    }

    static int kill(int x, int y) {
        int total = 0;
        for (int i = x; i < x + M; i++) {
            for (int j = y; j < y + M; j++) {
                total += map[i][j];
            }
        }
        return total;
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
