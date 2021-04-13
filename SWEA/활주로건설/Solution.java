package SWEA.활주로건설;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, X;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder result = new StringBuilder();
        int T = stoi(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            X = stoi(st.nextToken());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = stoi(st.nextToken());
                }
            }

            int count = 0;
            for (int i = 0; i < N; i++) {
                // row
                if (isPossible(i, 0)) count++;
                // col
                if (isPossible(i, 1)) count++;
            }
            result.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(result);
    }

    static boolean isPossible(int x, int flag) {
        if (flag == 0) {    // row 기준
            int currentHeight = map[x][0];
            boolean[] isInstalled = new boolean[N];
            for (int j = 1; j < N; j++) {
                int gap = Math.abs(currentHeight - map[x][j]);
                if (gap == 0) continue;
                else if (gap > 1) return false;
                else if (gap == 1) {
                    if (currentHeight > map[x][j]) {
                        for (int w = j; w < j + X; w++) {
                            if (w >= N || map[x][w] != map[x][j]) return false;
                            if (isInstalled[w]) return false;
                            isInstalled[w] = true;
                        }
                        currentHeight = map[x][j];
                        j += X - 1;
                    } else {
                        for (int w = j - 1; w > j - X - 1; w--) {
                            if (w < 0 || map[x][w] != currentHeight) return false;
                            if (isInstalled[w]) return false;
                            isInstalled[w] = true;
                        }
                        currentHeight = map[x][j];
                    }
                }
            }
        } else {
            int currentHeight = map[0][x];
            boolean[] isInstalled = new boolean[N];
            for (int i = 1; i < N; i++) {
                int gap = Math.abs(currentHeight - map[i][x]);
                if (gap == 0) continue;
                else if (gap > 1) return false;
                else if (gap == 1) {
                    if (currentHeight > map[i][x]) {
                        for (int h = i; h < i + X; h++) {
                            if (h >= N || map[h][x] != map[i][x]) return false;
                            if (isInstalled[h]) return false;
                            isInstalled[h] = true;
                        }
                        currentHeight = map[i][x];
                        i += X - 1;
                    } else {
                        for (int h = i - 1; h > i - X - 1; h--) {
                            if (h < 0 || map[h][x] != currentHeight) return false;
                            if (isInstalled[h]) return false;
                            isInstalled[h] = true;
                        }
                        currentHeight = map[i][x];
                    }
                }
            }
        }
        return true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
