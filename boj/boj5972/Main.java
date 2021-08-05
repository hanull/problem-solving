package boj.boj5972;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int vertex, dist;

        public Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }
    static List<Node>[] nodes;
    static int N, M;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        nodes = new List[N];
        distance = new int[N];
        for (int i = 0; i < N; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken()) - 1;
            int to = stoi(st.nextToken()) - 1;
            int dist = stoi(st.nextToken());
            nodes[from].add(new Node(to, dist));
            nodes[to].add(new Node(from, dist));
        }
        Arrays.fill(distance, 987654321);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((Comparator.comparingInt(o -> o.dist)));
        priorityQueue.add(new Node(0, 0));
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            int vertex = node.vertex;
            int preDist = node.dist;
            for (Node nextNode : nodes[vertex]) {
                int next = nextNode.vertex;
                int dist = nextNode.dist;
                if (distance[next] > preDist + dist) {
                    distance[next] = preDist + dist;
                    priorityQueue.add(new Node(next, distance[next]));
                }
            }
        }
        System.out.println(distance[N - 1]);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
