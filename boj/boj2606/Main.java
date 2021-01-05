package boj.boj2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static ArrayList<Integer>[] network;
    static boolean[] visited;
    static final int startPoint = 1;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = stoi(br.readLine());
        E = stoi(br.readLine());

        network = new ArrayList[V + 1];
        visited = new boolean[V + 1];

        for (int i = 1; i <= V; i++) {
            network[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            network[from].add(to);
            network[to].add(from);
        }

        bfs();
        System.out.println(res);
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(startPoint);
        visited[startPoint] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            for (Integer computer : network[node]) {
                if (visited[computer]) continue;
                visited[computer] = true;
                q.add(computer);
                res++;
            }
        }
    }

    public static int stoi(String input) {
        return Integer.valueOf(input);
    }

}
