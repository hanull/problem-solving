package boj.boj15649;

import java.util.Scanner;

public class Main {

    static int N, M;
    static boolean[] isSelected;
    static int[] numbers;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        isSelected = new boolean[N + 1];
        numbers = new int[M];
        dfs(0);
        System.out.print(answer);
    }

    private static void dfs(int count) {
        if (count == M) {
            for (int i = 0; i < M; i++) answer.append(numbers[i]).append(" ");
            answer.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;
            numbers[count] = i;
            dfs(count + 1);
            isSelected[i] = false;
        }
    }
}
