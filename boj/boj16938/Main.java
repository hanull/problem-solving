package boj.boj16938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, L, R, X, answer;
    static int[] scores;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        L = stoi(st.nextToken());
        R = stoi(st.nextToken());
        X = stoi(st.nextToken());
        scores = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scores[i] = stoi(st.nextToken());
        }
        Arrays.sort(scores);
        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int index, int count) {
        if (index == N) {
            if (count >= 2 && isPossible()) answer++;
            return;
        }
        // 해당 문제를 선택할 경우
        visited[index] = true;
        dfs(index + 1, count + 1);
        visited[index] = false;

        // 해당 문제를 선택하지 않을 경우
        dfs(index + 1, count);
    }

    private static boolean isPossible() {
        int total = 0, max = 0, min = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                total += scores[i];
                max = scores[i];
                if (min == 0) min = scores[i];
            }
        }
        if (total < L || total > R) return false;
        if (max - min < X) return false;
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
