package boj.boj13905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    static int N, M, start, end;
    static final int maxGoldBar = 1000000;
    static List<Node>[] vertexes;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(findMaximumGoldBar());
    }

    private static int findMaximumGoldBar() {
        int[] answer = new int[N];
        PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> o2.weight - o1.weight));
        boolean[] visited = new boolean[N];
        pq.add(new Node(start, 0));
        answer[start] = maxGoldBar;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int currentVertex = node.vertex;
            if (visited[currentVertex]) continue;
            visited[currentVertex] = true;
            for (Node nextNode : vertexes[node.vertex]) {
                int nextVertex = nextNode.vertex;
                int nextWeight = nextNode.weight;
                answer[nextVertex] = Math.max(answer[nextVertex], Math.min(answer[currentVertex], nextWeight));
                if (!visited[nextVertex]) pq.add(new Node(nextVertex, nextWeight));
            }
        }
        return answer[end];
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        vertexes = new List[N];
        for (int i = 0; i < N; i++) vertexes[i] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        start = stoi(st.nextToken()) - 1;
        end = stoi(st.nextToken()) - 1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken()) - 1;
            int to = stoi(st.nextToken()) - 1;
            int weight = stoi(st.nextToken());
            vertexes[from].add(new Node(to, weight));
            vertexes[to].add(new Node(from, weight));
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
