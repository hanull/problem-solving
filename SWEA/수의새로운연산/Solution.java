package SWEA.수의새로운연산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        getMapSize(0,0, 10000);
        map = new int[N + 1][N + 1];
        int num = 1;
        for (int i = 1; i <= N; i++) {
            map[i][1] = num;
            for (int j = 2; j <= N; j++) {
                map[i][j] = map[i][j - 1] + i + j - 1;
            }
            num += i + 1;
        }
        for (int tc = 1; tc <= T; tc++) {

        }

    }

    static void getMapSize(int num, int sum, int goal) {
        if (sum >= goal) {
            N = num;
            return;
        }
        getMapSize(num + 1, sum + num * 4, goal);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
