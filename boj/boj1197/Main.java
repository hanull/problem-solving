package boj.boj1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static int[] parent;
    static final int MAX_WEIGHT = 1000000;
    static List<Edge> edgeList = new ArrayList<>();
    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        parent = new int[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            edgeList.add(new Edge(from, to, weight + MAX_WEIGHT));
        }

        makeSet();
        System.out.println(kruskal() - (MAX_WEIGHT * (V - 1)));
    }

    static int kruskal() {
        int totalWeight = 0;
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(edgeList);

        while (!priorityQueue.isEmpty()) {
            Edge tmp = priorityQueue.poll();
            int from = tmp.from;
            int to = tmp.to;
            int weight = tmp.weight;

            if (union(from, to)) {
                totalWeight += weight;
            }
        }

        return totalWeight;
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x==y) return false;
        parent[y] = x;
        return true;
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void makeSet() {
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
    }
    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
