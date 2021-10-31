package boj.boj1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parent;
    static List<Node> list = new ArrayList<>();
    static class Node {
        int from, to, weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            list.add(new Node(from, to, weight));
        }
        Collections.sort(list, (Comparator.comparingInt(o -> o.weight)));
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        List<Integer> answerList = new ArrayList<>();
        for (Node node : list) {
            int from = node.from;
            int to = node.to;
            int weight = node.weight;
            if (!sameParent(from, to)) {
                union(from, to);
                answerList.add(weight);
            }
        }
        int answer = 0;
        for (int i = 0; i < answerList.size() - 1; i++) {
            answer += answerList.get(i);
        }
        System.out.println(answer);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    private static boolean sameParent(int x, int y) {
        return find(x) == find(y);
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
