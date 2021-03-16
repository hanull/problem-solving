package boj.boj1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static int N, M;
    static boolean[] visited;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        int start = stoi(st.nextToken());
        list = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            list[from].add(to);
            list[to].add(from);
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(list[i]);
        }

        dfs(start);
        System.out.println();
        bfs(start);
    }

    static void dfs(int v) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int next : list[v]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    static void bfs(int start) {
        visited = new boolean[N + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(start);
        visited[start] = true;

        while (!deque.isEmpty()) {
            int tmp = deque.pollFirst();
            System.out.print(tmp + " ");
            for (int next : list[tmp]) {
                if (!visited[next]) {
                    visited[next] = true;
                    deque.add(next);
                }
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
