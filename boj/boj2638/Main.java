package boj.boj2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Node> cheeseList = new ArrayDeque<>();
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        int answer = 0;
        while (true) {
            searchCheese();
            if (cheeseList.size() == 0) break;
            meltCheese();
            answer++;
        }
        System.out.println(answer);
    }

    private static void meltCheese() {
        while (!cheeseList.isEmpty()) {
            Node node = cheeseList.poll();
            map[node.x][node.y] = 0;
        }
    }

    private static void searchCheese() {
        cheeseList.clear();
        int[][] temp = new int[N][M];
        checkCount(temp);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] >= 2) cheeseList.add(new Node(i, j));
            }
        }
    }

    private static void checkCount(int[][] temp) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][4];
        q.add(new Node(0, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            int tx = node.x;
            int ty = node.y;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny][d]) continue;
                visited[nx][ny][d] = true;
                if (map[nx][ny] == 1) temp[nx][ny]++;
                else q.add(new Node(nx, ny));
            }
        }
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= M ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
