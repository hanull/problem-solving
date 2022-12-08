package naver_financial.test1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

class Solution {

    static List<Node>[] graph;
    static int[] answer = new int[2];
    static class Node {
        int vertex;

        public Node(final int vertex) {
            this.vertex = vertex;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Node node = (Node) o;
            return vertex == node.vertex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex);
        }
    }

    public int[] solution(int n, int[][] edges) {
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            graph[from].add(new Node(to));
            graph[to].add(new Node(from));
        }

        dfs(0, 0, new boolean[n], edges);

        return answer;
    }

    private void dfs(final int count, final int start, final boolean[] removed, final int[][] edges) {
        if (count == 2) {
            if (isSuccess()) {
                int index = 0;
                for (int i = 0; i < removed.length; i++) {
                    if (removed[i]) {
                        answer[index++] = i;
                    }
                }
            }
            return;
        }

        for (int i = start; i < removed.length - 1; i++) {
            if (removed[i]) {
                continue;
            }
            removed[i] = true;
            final int from = edges[i][0];
            final int to = edges[i][1];
            graph[from].remove(new Node(to));
            graph[to].remove(new Node(from));
            dfs(count + 1, i + 1, removed, edges);
            graph[from].add(new Node(to));
            graph[to].add(new Node(from));
            removed[i] = false;
        }
    }

    private boolean isSuccess() {
        int count = 0;
        boolean[] visited = new boolean[graph.length];
        Set<Integer> sameCountChecker = new HashSet<>();
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                count++;
                sameCountChecker.add(bfs(i, visited));
            }
        }
        return count == 3 && sameCountChecker.size() == 1;
    }

    private int bfs(final int v, final boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(v);
        visited[v] = true;
        int count = 1;

        while (!q.isEmpty()) {
            Integer current = q.poll();
            for (Node next : graph[current]) {
                if (!visited[next.vertex]) {
                    visited[next.vertex] = true;
                    q.add(next.vertex);
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(
                sol.solution(9, new int[][]{{0, 2}, {2, 1}, {2, 4}, {3, 5}, {5, 4}, {5, 7}, {7, 6}, {6, 8}})));
    }
}
