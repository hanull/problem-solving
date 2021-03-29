package boj.boj14923;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int minDistance = Integer.MAX_VALUE;
    static Node startNode, endNode;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y, dist, broken;

        public Node(int x, int y, int dist, int broken) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broken = broken;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine());
        startNode = new Node(stoi(st.nextToken()) - 1, stoi(st.nextToken()) - 1,0,  0);
        st = new StringTokenizer(br.readLine());
        endNode = new Node(stoi(st.nextToken()) - 1, stoi(st.nextToken()) - 1,0,  0);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        escape();
        System.out.println(minDistance == Integer.MAX_VALUE ? -1 : minDistance);
    }

    static void escape() {
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(startNode.x, startNode.y,0,  0));
        boolean[][][] visited = new boolean[N][M][2];
        visited[startNode.x][startNode.y][0] = true;

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            int dist = tmp.dist;
            int broken = tmp.broken;
            if (tx == endNode.x && ty == endNode.y) {
                minDistance = dist;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny][broken]) continue;
                if (map[nx][ny] == 1 && broken == 1) continue;
                visited[nx][ny][broken] = true;
                if (map[nx][ny] == 1) {
                    deque.add(new Node(nx, ny, dist + 1, 1));
                } else {
                    deque.add(new Node(nx, ny, dist + 1, broken));
                }
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
