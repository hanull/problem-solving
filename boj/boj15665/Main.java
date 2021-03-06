package boj.boj15665;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N, M;
    static int[] numbers;
    static int[] pick;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        numbers = new int[N];
        pick = new int[M];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }
        Arrays.sort(numbers);
        dfs(0);
        System.out.print(answer);
    }

    private static void dfs(int count) {
        if (count == M) {
            for (int i = 0; i < M; i++) answer.append(pick[i]).append(" ");
            answer.append("\n");
            return;
        }
        int pre = 0;
        for (int i = 0; i < N; i++) {
            if (pre == numbers[i]) continue;
            pre = numbers[i];
            pick[count] = numbers[i];
            dfs(count + 1);
        }
    }
}
