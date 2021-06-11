package boj.boj14712;

import java.util.Scanner;

public class Main {

    static int N, M;
    static int[][] map;
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N + 1][M + 1];
        dfs(1, 1);
        System.out.println(answer);
    }

    private static void dfs(int x, int y) {
        if (x == N && y == M + 1) {
            answer++;
            return;
        }
        for (int i = x; i <= N; i++) {
            int start = x == i ? y : 1;
            for (int j = start; j <= M; j++) {
                if (isNemo(i, j)) continue;
                map[i][j] = 1;
                dfs(i, j + 1);
                map[i][j] = 0;
            }
        }
        answer++;
    }

    private static boolean isNemo(int i, int j) {
        return map[i - 1][j] == 1 && map[i-1][j-1] == 1 && map[i][j-1] == 1;
    }

}
