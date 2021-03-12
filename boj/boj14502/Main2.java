package boj.boj14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static int N, M;
    static int[][] map, originMap;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] selectedPoint = new int[3];
    static int max;
    static Deque<Node> virusList = new ArrayDeque<>();
    static ArrayList<Node> emptyRoom = new ArrayList<>();
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
        originMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                originMap[i][j] = map[i][j];
                if (map[i][j] == 2) virusList.addLast(new Node(i, j));
                else if (map[i][j] == 0) emptyRoom.add(new Node(i, j));
            }
        }

        setWall(0, 0);
        System.out.println(max);
    }

    static void setWall(int cnt, int start) {
        if (cnt == 3) {
            copyMap();
            set();
            spreadVirus();
            searchSafeArea();
            return;
        }

        for (int i = start; i < emptyRoom.size(); i++) {
            selectedPoint[cnt] = i;
            setWall(cnt + 1, i + 1);
        }
    }

    static void spreadVirus() {
        Deque<Node> deque = new ArrayDeque<>(virusList);
        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == 0) {
                    map[nx][ny] = 2;
                    deque.add(new Node(nx, ny));
                }
            }
        }
    }

    static void searchSafeArea() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) total++;
            }
        }
        max = Math.max(max, total);
    }

    static void set() {
        for (int i : selectedPoint) {
            Node tmp = emptyRoom.get(i);
            map[tmp.x][tmp.y] = 1;
        }
    }

    static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = originMap[i][j];
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
