package SWEA.무인도탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int N, maxHeight;
    static int[][] boxes;
    static boolean[] isSelected;
    static final int[][] boxStatus = {{0, 1, 2}, {1, 2, 0}, {0, 2, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();
        int T = stoi(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = stoi(br.readLine());
            boxes = new int[N][3];
            isSelected = new boolean[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                boxes[i][0] = stoi(st.nextToken());
                boxes[i][1] = stoi(st.nextToken());
                boxes[i][2] = stoi(st.nextToken());
            }
            for (int i = 0; i < N; i++) {
                Arrays.sort(boxes[i]);
            }
            maxHeight = 0;
            dfs(10000, 10000, 0, 0);
            answer.append("#").append(tc).append(" ").append(maxHeight).append("\n");
        }
        System.out.println(answer);
    }

    private static void dfs(int w, int d, int index, int total) {
        if (index == N) return;
        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;
            for (int j = 0; j < 3; j++) {
                int nextW = boxes[i][boxStatus[j][0]];
                int nextD = boxes[i][boxStatus[j][1]];
                int nextH = boxes[i][boxStatus[j][2]];
                if (isPossible(w, d, nextW, nextD)) dfs(nextW, nextD, index + 1, total + nextH);
            }
            isSelected[i] = false;
        }
    }

    private static boolean isPossible(int w, int d, int nextW, int nextD) {
        return w >= nextW && d >= nextD;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
