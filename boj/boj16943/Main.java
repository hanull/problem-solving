package boj.boj16943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, target, answer;
    static char[] arr;
    static char[] result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = st.nextToken().toCharArray();
        N = arr.length;
        result = new char[N];
        visited = new boolean[N];
        target = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            if (arr[i] !='0' && arr[i] - '0' <= target / 10) {
                result[0] = arr[i];
                visited[i] = true;
                dfs(1);
                visited[i] = false;
            }
        }
        System.out.println(answer == 0 ? -1 : answer);
    }

    private static void dfs(int count) {
        if (count == N) {
            int num = Integer.parseInt(new String(result));
            if (num < target) {
                answer = Math.max(answer, num);
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            result[count] = arr[i];
            dfs(count + 1);
            visited[i] = false;
        }
    }
}
