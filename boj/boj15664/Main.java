package boj.boj15664;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N, M;
    static int[] numbers;
    static int[] pick;
    static boolean[] used;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        numbers = new int[N];
        pick = new int[M];
        used = new boolean[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }
        Arrays.sort(numbers);
        dfs(0, 0);
        System.out.print(answer);
    }

    private static void dfs(int index, int count) {
        if (count == M) {
            for (int i = 0; i < M; i++) answer.append(pick[i]).append(" ");
            answer.append("\n");
            return;
        }
        int pre = 0;
        for (int i = index; i < N; i++) {
            if (used[i] || pre == numbers[i]) continue;
            pre = numbers[i];
            used[i] = true;
            pick[count] = numbers[i];
            dfs(i + 1, count + 1);
            used[i] = false;
        }
    }
}
