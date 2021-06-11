package boj.boj15651;

import java.util.Scanner;

public class Main {

    static int N, M;
    static int[] pick;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        pick = new int[M];
        dfs(0);
        System.out.print(answer);
    }

    private static void dfs(int count) {
        if (count == M) {
            for (int i = 0; i < M; i++) answer.append(pick[i]).append(" ");
            answer.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            pick[count] = i;
            dfs(count + 1);
        }
    }
}
