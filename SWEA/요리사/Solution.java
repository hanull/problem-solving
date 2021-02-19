package SWEA.요리사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[][] map;
    static boolean[] isSelected;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = stoi(br.readLine());
            map = new int[N][N];
            isSelected = new boolean[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = stoi(st.nextToken());
                }
            }

            min = Integer.MAX_VALUE;
            selectRecipe(0, 0);
            sb.append("#" + tc + " " + min + "\n");
        }
        System.out.print(sb);
    }

    static void selectRecipe(int cnt, int start) {
        if (cnt == N / 2) {
            min = Math.min(min, calcGap());
            return;
        }

        for (int i = start; i < N; i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;
            selectRecipe(cnt + 1, i + 1);
            isSelected[i] = false;
        }
    }

    static int calcGap() {
        int totalA = 0;
        int totalB = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isSelected[i] && isSelected[j]) {
                    totalA += map[i][j] + map[j][i];
                } else if (!isSelected[i] && !isSelected[j]) {
                    totalB += map[i][j] + map[j][i];
                }
            }
        }
        return Math.abs(totalA - totalB);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
