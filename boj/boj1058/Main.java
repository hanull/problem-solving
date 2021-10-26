package boj.boj1058;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int N;
    static List<Integer>[] lists;
    static class Node {
        int v, dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lists = new List[N];
        for (int i = 0; i < N; i++) lists[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (input[j] == 'Y') {
                    lists[i].add(j);
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, findFriends(i));
        }
        System.out.println(answer);
    }

    private static int findFriends(int v) {
        int total = 0;
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        q.add(new Node(v, 0));
        visited[v] = true;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int current = node.v;
            int dist = node.dist;
            if (dist >= 2) continue;
            for (int next : lists[current]) {
                if (visited[next]) continue;
                visited[next] = true;
                q.add(new Node(next, dist + 1));
                total++;
            }
        }
        return total;
    }
}
