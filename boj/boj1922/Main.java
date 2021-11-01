package boj.boj1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
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
        StringTokenizer st;
        N = stoi(br.readLine());
        M = stoi(br.readLine());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int cost = stoi(st.nextToken());
            list.add(new Node(from, to, cost));
        }
        Collections.sort(list, (Comparator.comparingInt(o -> o.cost)));
        int answer = 0;
        for (Node node : list) {
            int from = node.from;
            int to = node.to;
            int cost = node.cost;
            if (find(from) != find(to)) {
                answer += cost;
                union(from, to);
            }
        }
        System.out.println(answer);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
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
