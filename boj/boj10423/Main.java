package boj.boj10423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] parent;
    static List<Node> list = new ArrayList<>();
    static class Node {
        int from, to, cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            parent[stoi(st.nextToken())] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int cost = stoi(st.nextToken());
            list.add(new Node(from, to, cost));
        }
        Collections.sort(list, Comparator.comparingInt(o -> o.cost));

        int answer = 0;
        for (Node node : list) {
            int from = node.from;
            int to = node.to;
            int cost = node.cost;
            if ((find(from) != find(to))) {
                answer += cost;
                union(from, to);
            }
        }
        System.out.println(answer);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
