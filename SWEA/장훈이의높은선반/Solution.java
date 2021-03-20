package SWEA.장훈이의높은선반;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, B, minValue;
    static int[] salesmanHeight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            B = stoi(st.nextToken());
            salesmanHeight = new int[N];
            st = new StringTokenizer(br.readLine());
            int totalHeight = 0;
            for (int i = 0; i < N; i++) {
                salesmanHeight[i] = stoi(st.nextToken());
                totalHeight += salesmanHeight[i];
            }

            minValue = Integer.MAX_VALUE;
            dfs(0, 0, totalHeight);
            sb.append("#").append(tc).append(" ").append(minValue).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int index, int height, int remain) {
        if (height + remain < B) return;
        if (height >= B) {
            minValue = Math.min(height - B, minValue);
            return;
        }
        if (index == N) return;
        dfs(index + 1, height + salesmanHeight[index], remain - salesmanHeight[index]);
        dfs(index + 1, height, remain);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
