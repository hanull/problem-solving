package boj.boj2234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[][][] isWall;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};   // 남,동,북,서
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static ArrayList<Wall> wallList = new ArrayList<>();
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Wall {
        int x, y, d;

        public Wall(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        isWall = new boolean[N][M][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = stoi(st.nextToken());
                String str = Integer.toBinaryString(num);
                int len = 4 - str.length();
                for (int k = str.length() - 1 + len; k >= 0 + len; k--) {
                    if (str.charAt(k - len) == '1') {
                        wallList.add(new Wall(i, j, k));
                        isWall[i][j][k] = true;
                    }
                }
            }
        }

        int roomCount = 0;
        int maxRoomSize = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    maxRoomSize = Math.max(maxRoomSize, searchRoom(i, j));
                    roomCount++;
                }
            }
        }

        int maxRoomSizeRemoveWall = 0;
        for (int i = 0; i < wallList.size(); i++) {
            visited = new boolean[N][M];
            Wall wall = wallList.get(i);
            int x = wall.x;
            int y = wall.y;
            int d = wall.d;
            isWall[x][y][d] = false;
            maxRoomSizeRemoveWall = Math.max(maxRoomSizeRemoveWall, searchRoom(x, y));
            isWall[x][y][d] = true;
        }

        System.out.println(roomCount);
        System.out.println(maxRoomSize);
        System.out.println(maxRoomSizeRemoveWall);

    }

    static int searchRoom(int x, int y) {
        int size = 1;
        Deque<Node> tmp = new ArrayDeque<>();
        tmp.add(new Node(x, y));
        visited[x][y] = true;

        while (!tmp.isEmpty()) {
            Node node = tmp.pollFirst();
            int tx = node.x;
            int ty = node.y;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (isWall[tx][ty][i]) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                tmp.add(new Node(nx, ny));
                size += 1;
            }
        }

        return size;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
