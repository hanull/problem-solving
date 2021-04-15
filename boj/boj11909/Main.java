package boj.boj11909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static class Node {
        int x, y, count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        System.out.println(solve());

    }

    static int solve() {
        int result = Integer.MAX_VALUE;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>((o1, o2) -> o1.count - o2.count);
        priorityQueue.add(new Node(0, 0, 0));
        visited[0][0][0] = true;

        while (!priorityQueue.isEmpty()) {
            Node tmp = priorityQueue.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int count = tmp.count;
            int size = map[tx][ty];
            if (tx == N - 1 && ty == N - 1) {
                result = count;
                break;
            }
            for (int d = 0; d < 2; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny][d]) continue;
                int nextSize = map[nx][ny];
                visited[nx][ny][d] = true;
                priorityQueue.add(new Node(nx, ny, size > nextSize ? count : count + nextSize - size + 1));
            }
        }

        return result;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
