package boj.boj1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node implements Comparable<Node>{
        int x, y, height;

        public Node(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        @Override
        public int compareTo(Node o) {
            return o.height - this.height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        move();
        System.out.println(dp[N - 1][M - 1]);
    }

    static void move() {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(0, 0, map[0][0]));
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        dp[0][0] = 1;

        while (!priorityQueue.isEmpty()) {
            Node tmp = priorityQueue.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int currentHeight = tmp.height;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] >= currentHeight) continue;
                if (!visited[nx][ny]) priorityQueue.add(new Node(nx, ny, map[nx][ny]));
                visited[nx][ny] = true;
                dp[nx][ny] += dp[tx][ty];
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
