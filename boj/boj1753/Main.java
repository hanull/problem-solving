package boj.boj1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int end;
    int dist;

    public Node(int end, int dist) {
        this.end = end;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.dist, o.dist);
    }
}

public class Main {

    static int V, E;
    static ArrayList<Node>[] list;
    static boolean[] visited;
    static int[] dist;
    static final int INF = 200000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        int startPoint = stoi(br.readLine());
        init(br, st);
        dijkstra(startPoint);
        printResult(dist);
    }

    private static void printResult(int[] dist) {
        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }

    private static void dijkstra(int startPoint) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[startPoint] = 0;
        pq.add(new Node(startPoint, 0));

        while (!pq.isEmpty()) {
            Node tmp = pq.poll();
            int currentPoint = tmp.end;
            if (visited[currentPoint]) continue;
            visited[currentPoint] = true;
            for (Node node : list[currentPoint]) {
                if (!visited[node.end] && dist[node.end] > node.dist + dist[currentPoint]) {
                    dist[node.end] = node.dist + dist[currentPoint];
                    pq.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }

    private static void init(BufferedReader br, StringTokenizer st) throws IOException {
        list = new ArrayList[V + 1];
        visited = new boolean[V + 1];
        dist = new int[V + 1];

        Arrays.fill(dist, INF);
        for (int i = 0; i <= V; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int d = stoi(st.nextToken());
            list[from].add(new Node(to, d));
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
