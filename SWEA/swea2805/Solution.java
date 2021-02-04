package SWEA.swea2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int T, N;
    static int[][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        T = stoi(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            init();
            int totalCount = cal();
            System.out.println("#" + tc + " " + totalCount);
        }
    }

    static int cal() {
        int total = 0;
        int mid = N / 2;
        int left = mid;
        int right = mid;
        for (int i = 0; i <= mid; i++) {
            for (int j = left; j <= right; j++) {
                total += map[i][j];
            }
            left--;
            right++;
        }
        left = right = mid;
        for (int i = N - 1; i > mid; i--) {
            for (int j = left; j <= right; j++) {
                total += map[i][j];
            }
            left--;
            right++;
        }
        return total;
    }

    static void init() throws IOException {
        N = stoi(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(input[j]);
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
