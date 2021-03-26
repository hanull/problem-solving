package SWEA.사람네트워크2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
2
5 0 1 1 0 0 1 0 1 1 1 1 1 0 0 0 0 1 0 0 0 0 1 0 0 0
5 0 0 1 1 0 0 0 1 0 0 1 1 0 0 1 1 0 0 0 1 0 0 1 1 0
 */
public class Solution_djikstra {

    static final int MAX = 100000000;
    static int N;
    static int[] distance;
    static List<Integer>[] vertexList;
    static class Node {
        int vertex, dist;

        public Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            vertexList = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                vertexList[i] = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    if (stoi(st.nextToken()) != 0) vertexList[i].add(j);
                }
            }

            result.append("#").append(tc).append(" ").append(findMinDistance()).append("\n");
        }
        System.out.print(result);

    }

    static int findMinDistance() {
        int result = MAX;

        for (int startVertex = 0; startVertex < N; startVertex++) {
            PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> o1.dist - o2.dist));
            distance = new int[N];
            boolean[] visited = new boolean[N];
            Arrays.fill(distance, MAX);
            distance[startVertex] = 0;
            pq.add(new Node(startVertex, 0));
            visited[startVertex] = true;

            while (!pq.isEmpty()) {
                Node tmp = pq.poll();
                int currentVertex = tmp.vertex;

                for (int nextVertex : vertexList[currentVertex]) {
                    if (visited[nextVertex]) continue;
                    visited[nextVertex] = true;
                    if (distance[nextVertex] > distance[currentVertex] + 1) {
                        distance[nextVertex] = distance[currentVertex] + 1;
                        pq.add(new Node(nextVertex, distance[nextVertex]));
                    }
                }
            }

            int total = 0;
            for (int i = 0; i < N; i++) {
                total += distance[i];
            }
            result = Math.min(result, total);
        }
        return result;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
