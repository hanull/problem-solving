package boj.boj14621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parent;
    static char[] gender;
    static List<Node> list = new ArrayList<>();
    static class Node {
        int from, to, dist;

        public Node(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        parent = new int[N + 1];
        gender = new char[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            gender[i] = st.nextToken().charAt(0);
        }
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int dist = stoi(st.nextToken());
            list.add(new Node(from, to, dist));
        }
        Collections.sort(list, Comparator.comparingInt(o -> o.dist));
        int answer = 0;
        int count = 0;
        for (Node node : list) {
            int from = node.from;
            int to = node.to;
            int dist = node.dist;
            if ((find(from) != find(to)) && (gender[from] != gender[to])) {
                answer += dist;
                count++;
                union(from, to);
            }
        }
        System.out.println(count != N - 1 ? -1 : answer);
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
