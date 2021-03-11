package SWEA.등산로조성;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static int maxValue, totalDist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            K = stoi(st.nextToken());
            map = new int[N][N];
            maxValue = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = stoi(st.nextToken());
                    if (map[i][j] > maxValue) maxValue = map[i][j];
                }
            }

            totalDist = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == maxValue) {
                        visited[i][j] = true;
                        findTrail(i, j, map[i][j], true, 1);
                        visited[i][j] = false;
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(totalDist).append("\n");
        }
        System.out.print(sb);
    }

    static void findTrail(int x, int y, int height, boolean flag, int cnt) {
        totalDist = Math.max(totalDist, cnt);
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isRange(nx, ny)) continue;
            if (visited[nx][ny]) continue;
            if (map[nx][ny] < height) {
                visited[nx][ny] = true;
                findTrail(nx, ny, map[nx][ny], flag, cnt + 1);
                visited[nx][ny] = false;
            } else {
                if (flag) {
                    if (map[nx][ny] - K < height) { // 깍아서 이동 가능 할 때
                        visited[nx][ny] = true;
                        findTrail(nx, ny, height - 1, false, cnt + 1);
                        visited[nx][ny] = false;
                    }
                }
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
