package SWEA.벽돌깨기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, W, H;
    static int[][] map;
    static boolean[] visited;
    static int[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            W = stoi(st.nextToken());
            H = stoi(st.nextToken());
            map = new int[W][H];
            isSelected = new int[W];
            visited = new boolean[W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = stoi(st.nextToken());
                }
            }
            setBall(0);

        }
        System.out.println(sb);
    }

    static void setBall(int cnt) {
        if (cnt == N) {
            shoot();
            return;
        }

        for (int i = 0; i < W; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            isSelected[cnt] = i;
            setBall(cnt + 1);
            visited[i] = false;
        }
    }

    static void shoot() {

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
