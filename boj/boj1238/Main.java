package boj.boj1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MAX = 987654321;
    static int N, M, X;
    static List<Node>[] roadList;
    static class Node {
        int to, dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        X = stoi(st.nextToken()) - 1;
        roadList = new List[N];
        for (int i = 0; i < N; i++) {
            roadList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken()) - 1;
            int to = stoi(st.nextToken()) - 1;
            int dist = stoi(st.nextToken());
            roadList[from].add(new Node(to, dist));
        }
        int answer = 0;
        int[] returnHouse = dijkstraReturnHouse();
        for (int i = 0; i < N; i++) {
            int total = dijkstra(i) + returnHouse[i];
            answer = Math.max(total, answer);
        }
        System.out.println(answer);
    }

    private static int[] dijkstraReturnHouse() {
        int[] distArray = new int[N];
        Arrays.fill(distArray, MAX);
        distArray[X] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o.dist)));
        pq.add(new Node(X, 0));
        while (!pq.isEmpty()) {
            Node tmp = pq.poll();
            int currentVertex = tmp.to;
            for (Node node : roadList[currentVertex]) {
                int nextVertex = node.to;
                int dist = node.dist;
                if (distArray[nextVertex] > distArray[currentVertex] + dist) {
                    distArray[nextVertex] = distArray[currentVertex] + dist;
                    pq.add(new Node(nextVertex, distArray[nextVertex]));
                }
            }
        }
        return distArray;
    }

    private static int dijkstra(int v) {
        int[] distArray = new int[N];
        Arrays.fill(distArray, MAX);
        distArray[v] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o.dist)));
        pq.add(new Node(v, 0));
        while (!pq.isEmpty()) {
            Node tmp = pq.poll();
            int currentVertex = tmp.to;
            for (Node node : roadList[currentVertex]) {
                int nextVertex = node.to;
                int dist = node.dist;
                if (distArray[nextVertex] > distArray[currentVertex] + dist) {
                    distArray[nextVertex] = distArray[currentVertex] + dist;
                    pq.add(new Node(nextVertex, distArray[nextVertex]));
                }
            }
        }
        return distArray[X];
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
