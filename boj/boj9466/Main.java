package boj.boj9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, N;
    static int[] arr;
    static boolean[] visited;
    static boolean[] checkedCicle;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = stoi(br.readLine());

        while (T-- > 0) {
            N = stoi(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[N + 1];
            visited = new boolean[N + 1];
            checkedCicle = new boolean[N + 1];
            for (int i = 1; i <= N; i++) {
                arr[i] = stoi(st.nextToken());
            }

            cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (visited[i]) continue;
                dfs(i);
            }

            System.out.println(N - cnt);
        }
    }

    static void dfs(int current) {
        visited[current] = true;
        int next = arr[current];
        if (!visited[next]) {
            dfs(next);
        } else if (!checkedCicle[next]){
            cnt++;
            int now = arr[next];
            while (now != next) {
                now = arr[now];
                cnt++;
            }
        }
        checkedCicle[current] = true;
    }

    static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
