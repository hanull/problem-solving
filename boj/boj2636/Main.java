package boj.boj2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Node> air = new LinkedList<>();
    static ArrayList<Integer> leftOver = new ArrayList<>();
    static int cheeseSize = 0;
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] == 1) cheeseSize++;
            }
        }

        leftOver.add(cheeseSize);
        melt();

        System.out.println(time);
        System.out.println(leftOver.get(time - 1));
    }

    static void melt() {
        Queue<Node> melted = new LinkedList<>();
        while (cheeseSize > 0) {
            findAir();
            int size = air.size();
            int cnt = 0;

            while (!air.isEmpty()) {
                Node tmp = air.poll();
                int tx = tmp.x;
                int ty = tmp.y;
                for (int i = 0; i < 4; i++) {
                    int nx = tx + dx[i];
                    int ny = ty + dy[i];
                    if (!isRange(nx, ny)) continue;
                    if (map[nx][ny] == 0) continue;
                    if (visited[nx][ny]) continue;
                    melted.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            cheeseSize -= melted.size();
            leftOver.add(cheeseSize);
            time++;

            while (!melted.isEmpty()) {
                Node temp = melted.poll();
                map[temp.x][temp.y] = 0;
            }

        }
    }

    static void findAir() {
        Queue<Node> q = new LinkedList<>();
        visited = new boolean[N][M];
        q.add(new Node(0, 0));
        visited[0][0] = true;
        while (!q.isEmpty()) {
            Node tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            air.add(tmp);

            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == 1) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new Node(nx, ny));
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
