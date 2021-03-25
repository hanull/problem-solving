package SWEA.사람네트워크2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static final int MAX = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = stoi(st.nextToken());
            int[][] vertex = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num = stoi(st.nextToken());
                    if (i == j) vertex[i][j] = 0;
                    else {
                        vertex[i][j] = num == 0 ? MAX : num;
                    }
                }
            }

            for (int mid = 0; mid < N; mid++) {
                for (int from = 0; from < N; from++) {
                    for (int to = 0; to < N; to++) {
                        vertex[from][to] = Math.min(vertex[from][to], vertex[from][mid] + vertex[mid][to]);
                    }
                }
            }

            int minDistance = MAX;
            for (int from = 0; from < N; from++) {
                int total = 0;
                for (int to = 0; to < N; to++) {
                    total += vertex[from][to];
                }
                minDistance = Math.min(minDistance, total);
            }

            result.append("#").append(tc).append(" ").append(minDistance).append("\n");
        }
        System.out.print(result);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
