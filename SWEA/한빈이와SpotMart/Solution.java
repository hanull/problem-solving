package SWEA.한빈이와SpotMart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, M;
    static int[] weight;
    static int totalWeight;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int T = stoi(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());
            weight = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weight[i] = stoi(st.nextToken());
            }
            totalWeight = -1;
            comb(0, 0, 0);
            System.out.println("#" + tc + " " + totalWeight);
        }

    }

    static void comb(int cnt, int idx, int tmpWeight) {
        if (cnt == 2) {
            if (tmpWeight <= M) {
                totalWeight = Math.max(totalWeight, tmpWeight);
            }
            return;
        }

        for (int i = idx; i < N; i++) {
            comb(cnt + 1, i + 1, tmpWeight + weight[i]);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
