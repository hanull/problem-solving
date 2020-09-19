package boj.boj1325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] net;
    static int[] computer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.valueOf(st.nextToken()) - 1;
            int to = Integer.valueOf(st.nextToken()) - 1;
            net[from].add(to);
        }
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i);
        }
        int max = getMaxCount();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (computer[i] == max) {
                sb.append(i + 1 + " ");
            }
        }
        System.out.println(sb.toString());
    }

    private static int getMaxCount() {
        int res = 0;
        for (int i = 0; i < N; i++) {
            if (res < computer[i]) {
                res = computer[i];
            }
        }
        return res;
    }

    private static void init() {
        net = new ArrayList[N];
        computer = new int[N];
        for (int i = 0; i < N; i++) {
            net[i] = new ArrayList<>();
        }
    }

    private static void dfs(int from) {
        visited[from] = true;
        for (int v : net[from]) {
            if (!visited[v]) {
                computer[v]++;
                dfs(v);
            }
        }
    }
}
