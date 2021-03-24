package boj.boj14938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, R;
    static int[] itemCount;
    static ArrayList<Node>[] vertexList;
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
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        R = stoi(st.nextToken());
        itemCount = new int[N + 1];
        vertexList = new ArrayList[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            itemCount[i] = stoi(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            vertexList[i] = new ArrayList<>();
        }
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int dist = stoi(st.nextToken());
            vertexList[from].add(new Node(to, dist));
            vertexList[to].add(new Node(from, dist));
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(findAllItem(i), max);
        }
        System.out.println(max);
    }

    static int findAllItem(int startVertex) {
        int total = 0;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(((o1, o2) -> o1.dist - o2.dist));
        priorityQueue.add(new Node(startVertex, 0));
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startVertex] = 0;

        while (!priorityQueue.isEmpty()) {
            Node tmp = priorityQueue.poll();
            int currentVertex = tmp.vertex;
            for (Node nextNode : vertexList[currentVertex]) {
                int nextVertex = nextNode.vertex;
                int weight = nextNode.dist;
                if (distance[nextVertex] > distance[currentVertex] + weight) {
                    distance[nextVertex] = distance[currentVertex] + weight;
                    priorityQueue.add(new Node(nextVertex, distance[currentVertex] + weight));
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (distance[i] <= M) {
                total += itemCount[i];
            }
        }
        return total;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
