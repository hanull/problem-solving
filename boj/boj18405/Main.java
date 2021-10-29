package boj.boj18405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0 ,0};
    static Queue<Node> virus = new ArrayDeque<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o.no)));
    static class Node {
        int x, y, no;

        public Node(int x, int y, int no) {
            this.x = x;
            this.y = y;
            this.no = no;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        int K = stoi(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] > 0) virus.add(new Node(i, j, map[i][j]));
            }
        }
        st = new StringTokenizer(br.readLine());
        int S = stoi(st.nextToken());
        int targetX = stoi(st.nextToken()) - 1;
        int targetY = stoi(st.nextToken()) - 1;
        int time = 0;
        while (time++ < S) {
            spread();
        }
        System.out.println(map[targetX][targetY]);
    }

    private static void spread() {
        while (!virus.isEmpty()) {
            pq.add(virus.poll());
        }
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int tx = node.x;
            int ty = node.y;
            int no = node.no;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] > 0) continue;
                map[nx][ny] = no;
                virus.add(new Node(nx, ny, no));
            }
        }
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
