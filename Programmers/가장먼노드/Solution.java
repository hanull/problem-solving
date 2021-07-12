package Programmers.가장먼노드;

import java.util.*;

public class Solution {

    class Node {
        int vertex, dist;

        public Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<Integer>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) list[i] = new ArrayList<>();
        for (int i = 0; i < edge.length; i++) {
            int from = edge[i][0] - 1;
            int to = edge[i][1] - 1;
            list[to].add(from);
            list[from].add(to);
        }
        int[] distArray = new int[n];
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.add(new Node(0, 0));
        visited[0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int vertex = node.vertex;
            int dist = node.dist;
            answer = dist;
            for (int nextVertex : list[vertex]) {
                if (visited[nextVertex]) continue;
                visited[nextVertex] = true;
                distArray[dist + 1] += 1;
                queue.add(new Node(nextVertex, dist + 1));
            }
        }
        return distArray[answer];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(sol.solution(n, edge));
    }
}
