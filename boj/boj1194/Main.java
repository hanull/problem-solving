package boj.boj1194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;
    static class Node {
        int x, y, distance, key;

        public Node(int x, int y, int distance, int key) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.key = key;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new char[N][M];
        int startX = 0;
        int startY = 0;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            map[i] = input.toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0') {
                    startX = i;
                    startY = j;
                }
            }
        }

        move(startX, startY);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }

    private static void move(int startX, int startY) {
        Deque<Node> deque = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][64];
        deque.add(new Node(startX, startY, 0, 0));
        visited[startX][startY][0] = true;

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            int distance = tmp.distance;
            int key = tmp.key;
            if (map[tx][ty] == '1') {
                answer = distance;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == '#') continue;
                if (visited[nx][ny][key]) continue;

                if (map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') {
                    if ((key & 1 << map[nx][ny] - 'A') == 0) continue;
                    visited[nx][ny][key] = true;
                    deque.add(new Node(nx, ny, distance + 1, key));
                } else if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {
                    int nextKey = 1 << map[nx][ny] - 'a';
                    nextKey |= key;
                    if (!visited[nx][ny][nextKey]) {
                        visited[nx][ny][nextKey] = true;
                        deque.add(new Node(nx, ny, distance + 1, nextKey));
                    }
                } else {
                    visited[nx][ny][key] = true;
                    deque.add(new Node(nx, ny, distance + 1, key));
                }

            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }


    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
