package boj.boj15663;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static int N, M;
    static int[] numbers;
    static int[] pick;
    static boolean[] used;
    static HashSet<String> hashSet = new HashSet<>();
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
        dfs(0);
        System.out.print(answer);
    }

    private static void dfs(int count) {
        if (count == M) {
            if (!isPossible()) return;
            for (int i = 0; i < M; i++) answer.append(pick[i]).append(" ");
            answer.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            if (used[i]) continue;
            used[i] = true;
            pick[count] = numbers[i];
            dfs(count + 1);
            used[i] = false;
        }
    }

    private static boolean isPossible() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < M; i++) temp.append(pick[i]);
        if (hashSet.contains(temp.toString())) return false;
        hashSet.add(temp.toString());
        return true;
    }
}
