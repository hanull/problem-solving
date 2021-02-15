package boj.boj10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {

    static int N;
    static int[][] map, map2;
    static boolean[][] visited, visited2;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        map = new int[N][N];
        map2 = new int[N][N];
        visited = new boolean[N][N];
        visited2 = new boolean[N][N];
        // 적녹색약 -> R,G=0,   B=1
        // 일반    -> R=0, G=2, B=1
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                if (str.charAt(j) == 'B') {
                    map[i][j] = 1;
                    map2[i][j] = 1;
                }
                if (str.charAt(j) == 'G') {
                    map2[i][j] = 2;
                }
            }
        }

        int cnt = 0;
        int nomalCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    cnt++;
                    searchArea(i, j, false);
                }
                if (!visited2[i][j]) {
                    nomalCnt++;
                    searchArea(i, j, true);
                }
            }
        }
        System.out.println(nomalCnt + " " + cnt);

    }

    static void searchArea(int x, int y, boolean flag) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        if (!flag) {
            visited[x][y] = true;
        } else {
            visited2[x][y] = true;
        }

        while (!q.isEmpty()) {
            Node tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (!flag) {
                    if (visited[nx][ny]) continue;
                    if (map[nx][ny] == map[x][y]) {
                        visited[nx][ny] = true;
                        q.add(new Node(nx, ny));
                    }
                } else {
                    if (visited2[nx][ny]) continue;
                    if (map2[nx][ny] == map2[x][y]) {
                        visited2[nx][ny] = true;
                        q.add(new Node(nx, ny));
                    }
                }

            }
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
