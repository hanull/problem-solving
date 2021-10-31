package boj.boj1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

    static final int MAX = 100000;
    static int N, K;
    static class Node {
        int point, dist;

        public Node(int point, int dist) {
            this.point = point;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[MAX + 1];
        q.add(new Node(N, 0));
        visited[N] = true;
        int answer = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int tx = node.point;
            int dist = node.dist;
            int nx = tx - 1;
            if (tx == K) {
                answer = dist;
                break;
            }
            if (isRange(nx) && !visited[nx]){
                visited[nx] = true;
                q.add(new Node(nx, dist + 1));
            }
            nx = tx + 1;
            if (isRange(nx) && !visited[nx]){
                visited[nx] = true;
                q.add(new Node(nx, dist + 1));
            }
            nx = tx * 2;
            if (isRange(nx) && !visited[nx]){
                visited[nx] = true;
                q.add(new Node(nx, dist + 1));
            }
        }
        System.out.println(answer);
    }

    private static boolean isRange(int nx) {
        return nx < 0 || nx > MAX ? false : true;
    }
}
