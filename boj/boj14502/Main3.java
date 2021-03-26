package boj.boj14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main3 {

    static int N, M;
    static int[][] map, originMap;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Node> emptyRoom = new ArrayList<>();
    static List<Node> virusList = new ArrayList<>();
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
                if (map[i][j] == 0) emptyRoom.add(new Node(i, j));
                else if (map[i][j] == 2) virusList.add(new Node(i, j));
            }
        }

        int maxSafeArea = 0;
        int emptyRoomSize = emptyRoom.size();
        for (int i = 0; i < emptyRoomSize - 2; i++) {
            for (int j = i + 1; j < emptyRoomSize - 1; j++) {
                for (int k = j + 1; k < emptyRoomSize; k++) {
                    copyMap();
                    map[emptyRoom.get(i).x][emptyRoom.get(i).y] = map[emptyRoom.get(j).x][emptyRoom.get(j).y]
                            = map[emptyRoom.get(k).x][emptyRoom.get(k).y] = 1;
                    spreadVirus();
                    maxSafeArea = Math.max(maxSafeArea, calcSafeArea());
                }
            }
        }
        System.out.println(maxSafeArea);

    }

    static int calcSafeArea() {
        int total = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) total++;
            }
        }
        return total;
    }

    static void spreadVirus() {
        Deque<Node> deque = new ArrayDeque<>(virusList);
        boolean[][] visited = new boolean[N][M];

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    map[nx][ny] = 2;
                    deque.add(new Node(nx, ny));
                }
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = originMap[i][j];
            }
        }
    }
    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
