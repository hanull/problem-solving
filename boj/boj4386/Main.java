package boj.boj4386;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] parent;
    static List<Node> list = new ArrayList<>();
    static class Node {
        int from, to;
        double dist, x, y;

        public Node(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        Node[] stars = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[i] = new Node(x, y);
        }
        for (int i = 1; i <= N; i++) {
            double tx = stars[i].x;
            double ty = stars[i].y;
            for (int j = i + 1; j <= N; j++) {
                double nx = stars[j].x;
                double ny = stars[j].y;
                list.add(new Node(i, j, getDistance(tx, ty, nx, ny)));
            }
        }
        Collections.sort(list, Comparator.comparingDouble(o -> o.dist));
        double answer = 0;
        for (Node node : list) {
            int from = node.from;
            int to = node.to;
            double dist = node.dist;
            if (find(from) != find(to)) {
                answer += dist;
                union(from, to);
            }
        }
        System.out.println(String.format("%.2f", answer));
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static double getDistance(double tx, double ty, double nx, double ny) {
        double totalX = (tx - nx) * (tx - nx);
        double totalY = (ty - ny) * (ty - ny);
        return Math.sqrt(totalX + totalY);
    }
}
