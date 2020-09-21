package boj.boj1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int endPoint;
    int distance;

    public Node(int endPoint, int distance) {
        this.endPoint = endPoint;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.distance, o.distance);
    }
}

public class Main {

    static int N, M;
    static ArrayList<Node>[] list;
    static int[] minDistance;
    static boolean[] visited;
    static final int INF = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        M = stoi(br.readLine());
        list = new ArrayList[N];
        minDistance = new int[N];
        visited = new boolean[N];

        Arrays.fill(minDistance, INF);
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken()) - 1;
            int to = stoi(st.nextToken()) - 1;
            int dist = stoi(st.nextToken());
            list[from].add(new Node(to, dist));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = stoi(st.nextToken()) - 1;
        int end = stoi(st.nextToken()) - 1;
        solve(start);
        System.out.println(minDistance[end]);
    }

    private static void solve(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        minDistance[start] = 0;
        while (!pq.isEmpty()) {
            Node tmp = pq.poll();
            int current = tmp.endPoint;

            if (visited[current]) continue;
            visited[current] = true;
            for (Node node : list[current]) {   // 현재 정점에서 인접한 모든 정점 갱신
                if (!visited[node.endPoint] && minDistance[node.endPoint] > minDistance[current] + node.distance) {
                    minDistance[node.endPoint] = minDistance[current] + node.distance;
                    pq.add(new Node(node.endPoint, minDistance[node.endPoint]));
                }
            }
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
