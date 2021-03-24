package SWEA.하나로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static double E;
    static int[] parent;
    static PriorityQueue<Node> EdgeList;
    static class Node implements Comparable<Node>{
        int from, to;
        long weight;

        public Node(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = stoi(br.readLine());
            parent = new int[N + 1];
            EdgeList = new PriorityQueue<>();
            int[] x = new int[N + 1];
            int[] y = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                x[i] = stoi(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                y[i] = stoi(st.nextToken());
            }
            E = Double.parseDouble(br.readLine());

            for (int i = 1; i < N; i++) {
                int fromX = x[i];
                int fromY = y[i];
                for (int j = i + 1; j <= N; j++) {
                    int toX = x[j];
                    int toY = y[j];
                    EdgeList.add(new Node(i, j, (long) (fromX - toX) * (fromX - toX) + (long) (fromY - toY) * (fromY - toY)));
                }
            }
            makeSet();

            double totalCost = 0;
            int connectCount = 0;
            while (!EdgeList.isEmpty()) {
                if (connectCount == N - 1) break;
                Node node = EdgeList.poll();
                int from = node.from;
                int to = node.to;
                long weight = node.weight;
                if (union(from, to)) {
                    totalCost += weight;
                    connectCount++;
                }
            }
            totalCost *= E;
            result.append("#").append(tc).append(" ").append(Math.round(totalCost)).append("\n");
        }

        System.out.print(result);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;

        if (x != y) {
            parent[y] = x;
        }
        return true;
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void makeSet() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
