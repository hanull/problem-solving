package boj.boj1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(input[j]);
            }
        }

        recursive(0, 0, N);
        System.out.println(sb);
    }

    static void recursive(int x, int y, int len) {
        if (isPossible(x, y, len) || len == 1) {
            sb.append(map[x][y]);
            return;
        }

        sb.append("(");
        recursive(x, y, len / 2);
        recursive(x, y + len / 2, len / 2);
        recursive(x + len / 2, y, len / 2);
        recursive(x + len / 2, y + len / 2, len / 2);
        sb.append(")");
    }

    static boolean isPossible(int x, int y, int len) {
        int num = map[x][y];
        for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                if (map[i][j] != num) return false;
            }
        }
        return true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
