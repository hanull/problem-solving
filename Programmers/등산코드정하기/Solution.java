package Programmers.등산코드정하기;

import java.util.*;

public class Solution {

    static class Node {
        int vertex, weight;

        public Node(final int vertex, final int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    static Set<Integer> destinations = new HashSet<>();

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<Set<Node>> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodes.add(new HashSet<>());
        }
        for (int summit : summits) {
            destinations.add(summit);
        }

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int weight = path[2];
            nodes.get(from).add(new Node(to, weight));
            nodes.get(to).add(new Node(from, weight));
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        move(gates, nodes, dist);

        int[] answer = new int[]{0, Integer.MAX_VALUE};
        for (int summit : summits) {
            if (answer[1] > dist[summit]) {
                answer[1] = dist[summit];
                answer[0] = summit;
            } else if (answer[1] == dist[summit] && summit < answer[0]) {
                answer[0] = summit;
            }
        }
        return answer;
    }

    private void move(final int[] gates, final List<Set<Node>> nodes, final int[] dist) {
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        for (int gate : gates) {
            q.add(new Node(gate, 0));
            dist[gate] = 0;
        }

        while (!q.isEmpty()) {
            Node node = q.poll();
            int vertex = node.vertex;

            if (dist[vertex] < node.weight) {
                continue;
            }

            if (isDestination(vertex)) {
                continue;
            }
            for (Node next : nodes.get(vertex)) {
                int nextVertex = next.vertex;
                int nextWeight = Math.max(dist[vertex], next.weight);

                if (dist[nextVertex] > nextWeight) {
                    dist[nextVertex] = nextWeight;
                    q.add(new Node(nextVertex, nextWeight));
                }
            }
        }
    }

    private boolean isDestination(final int vertex) {
        return destinations.contains(vertex);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(6,
                new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}},
                new int[]{1, 3},
                new int[]{5}
        )));
        System.out.println(Arrays.toString(sol.solution(7,
                new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}},
                new int[]{1},
                new int[]{2, 3, 4}
        )));
    }
}
