package boj.boj14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int max;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] isSelected = new int[3];
    static int[][] map, mapOrigin;
    static ArrayList<Node> virusList = new ArrayList<>();
    static ArrayList<Node> blankRoom = new ArrayList<>();
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
        mapOrigin = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                mapOrigin[i][j] = map[i][j];
                if (map[i][j] == 2) virusList.add(new Node(i, j));
                if (map[i][j] == 0) blankRoom.add(new Node(i, j));
            }
        }
        combination(0, 0);
        System.out.println(max);
    }

    static void combination(int cnt, int start) {
        if (cnt == 3) {
            copyMap();
            makeWall();
            spreadVirus();
            max = Math.max(max, getSafetyArea());
            return;
        }

        for (int i = start; i < blankRoom.size(); i++) {
            isSelected[cnt] = i;
            combination(cnt + 1, i + 1);
        }
    }

    static int getSafetyArea() {
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) res++;
            }
        }
        return res;
    }

    static void spreadVirus() {
        for (Node node : virusList) {
            Queue<Node> q = new LinkedList<>();
            q.add(node);
            while (!q.isEmpty()) {
                Node tmp = q.poll();
                int tx = tmp.x;
                int ty = tmp.y;
                for (int i = 0; i < 4; i++) {
                    int nx = tx + dx[i];
                    int ny = ty + dy[i];
                    if (!isRange(nx, ny)) continue;
                    if (map[nx][ny] == 1 || map[nx][ny] == 2) continue;
                    map[nx][ny] = 2;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static void makeWall() {
        for (int idx : isSelected) {
            Node node = blankRoom.get(idx);
            map[node.x][node.y] = 1;
        }
    }

    static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = mapOrigin[i][j];
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
