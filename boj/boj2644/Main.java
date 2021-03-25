package boj.boj2644;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startPoint = stoi(st.nextToken());
        int endPoint = stoi(st.nextToken());
        List<Node>[] vertexList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            vertexList[i] = new ArrayList<>();
        }

        int M = stoi(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            vertexList[from].add(new Node(to, 1));
            vertexList[to].add(new Node(from, 1));
        }

        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(startPoint, 0));
        boolean[] visited = new boolean[N + 1];
        visited[startPoint] = true;
        int result = -1;
        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int currentVertex = tmp.vertex;
            int dist = tmp.dist;
            if (currentVertex == endPoint) {
                result = dist;
                break;
            }
            for (Node next : vertexList[currentVertex]) {
                int nextVertex = next.vertex;
                if (visited[nextVertex]) continue;
                visited[nextVertex] = true;
                deque.add(new Node(nextVertex, dist + 1));
            }
        }
        System.out.println(result);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
