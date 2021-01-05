package boj.boj1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, V;
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        V = stoi(st.nextToken());
        graph = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            graph[from][to] = graph[to][from] = 1;
        }

        dfs(V);
        System.out.println();
        bfs();
    }

    private static void dfs(int node) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int i = 1; i <= N; i++) {
            if (graph[node][i] == 0) continue;
            if (visited[i]) continue;
            dfs(i);
        }
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[N + 1];
        q.add(V);
        visited[V] = true;
        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
            for (int i = 1; i <= N; i++) {
                if (visited[i]) continue;
                if (graph[node][i] == 0) continue;
                q.add(i);
                visited[i] = true;
            }
        }
    }

    public static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
