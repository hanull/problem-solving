package boj.boj15652;

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
        dfs(1, 0);
        System.out.print(answer);
    }

    private static void dfs(int index, int count) {
        if (count == M) {
            for (int i = 0; i < M; i++) answer.append(pick[i]).append(" ");
            answer.append("\n");
            return;
        }
        for (int i = index; i <= N; i++) {
            pick[count] = i;
            dfs(i,count + 1);
        }
    }
}
