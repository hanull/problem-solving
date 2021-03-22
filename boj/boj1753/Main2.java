package boj.boj1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static int V, E;
    static int[] minDistance;
    static final int MAX = Integer.MAX_VALUE;
    static List<Node>[] vertexList;
    static class Node {
        int vertex, dist;

        public Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        int startVertex = stoi(br.readLine());
        minDistance = new int[V + 1];
        Arrays.fill(minDistance, MAX);
        vertexList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            vertexList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            vertexList[from].add(new Node(to, weight));
        }

        dijkstra(startVertex);
        printResult();
    }

    static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=V; i++) {
            if (minDistance[i] == MAX) {
                sb.append("INF");
            } else {
                sb.append(minDistance[i]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void dijkstra(int startVertex) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        boolean[] visited = new boolean[V + 1];
        visited[startVertex] = true;
        minDistance[startVertex] = 0;
        priorityQueue.add(new Node(startVertex, 0));

        while (!priorityQueue.isEmpty()) {
            Node tmp = priorityQueue.poll();
            int currentVertex = tmp.vertex;
            for (Node nextNode : vertexList[currentVertex]) {
                if (minDistance[nextNode.vertex] > nextNode.dist + minDistance[currentVertex]) {
                    minDistance[nextNode.vertex] = nextNode.dist + minDistance[currentVertex];
                    visited[nextNode.vertex] = true;
                    priorityQueue.add(new Node(nextNode.vertex, minDistance[nextNode.vertex]));
                }
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
