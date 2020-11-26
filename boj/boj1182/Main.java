package boj.boj1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S;
    static int total = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        S = stoi(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(st.nextToken());
        }
        dfs(0, 0);
        System.out.println(total);
    }

    private static void dfs(int idx, int cnt) {
        if (idx == N) return;
        if (cnt + arr[idx] == S) total++;

        dfs(idx + 1, cnt + arr[idx]);
        dfs(idx + 1, cnt);
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
