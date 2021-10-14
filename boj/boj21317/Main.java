package boj.boj21317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] narrow, wide;
    static int N, K;
    static int answer = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        StringTokenizer st;
        narrow = new int[N];
        wide = new int[N];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            // 작은 점프, 큰 점프
            narrow[i] = stoi(st.nextToken());
            wide[i] = stoi(st.nextToken());
        }
        K = stoi(br.readLine());
        dfs(0, 0, false);
        System.out.println(answer);
    }

    private static void dfs(int index, int total, boolean flag) {
        if (index == N - 1) {
            answer = Math.min(answer, total);
            return;
        }
        if (index + 1 < N) {
            dfs(index + 1, total + narrow[index], flag);
        }
        if (index + 2 < N) {
            dfs(index + 2, total + wide[index], flag);
        }
        if (index + 3 < N && !flag) {
            dfs(index + 3, total + K, true);
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
