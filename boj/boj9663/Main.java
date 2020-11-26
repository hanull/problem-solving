package boj.boj9663;

import java.util.Scanner;

public class Main {

    static int N;
    static int total = 0;
    static int[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        if (N == 1) {
            System.out.println(1);
        } else {
            visited = new int[N][N];
            dfs(0);
            System.out.println(total);
        }
    }

    private static void dfs(int x) {
        if (x == N) {
            total++;
            return;
        }
        for (int j = 0; j < N; j++) {
            if (visited[x][j] > 0) continue;
            checkVisited(x, j, 0);
            dfs(x + 1);
            checkVisited(x, j, 1);
        }
    }

    private static void checkVisited(int x, int y, int flag) {
        int check = flag == 0 ? 1 : -1;
        for (int i = x; i < N; i++) {   // 열 체크
            visited[i][y] += check;
        }
        int ty = y + 1;
        for (int i = x + 1; i < N; i++) {   // 대각선 오른쪽
            if (ty == N) break;
            visited[i][ty] += check;
            ty++;
        }
        ty = y - 1;
        for (int i = x + 1; i < N; i++) {   // 대각선 왼쪽
            if (ty < 0) break;
            visited[i][ty] += check;
            ty--;
        }
    }
}