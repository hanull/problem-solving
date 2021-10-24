package boj.boj18428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static final int WALL_COUNT = 3;
    static char[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static List<Node> teacher = new ArrayList<>();
    static List<Node> start = new ArrayList<>();
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') teacher.add(new Node(i, j));
                else if (map[i][j] == 'X') start.add(new Node(i, j));
            }
        }
        dfs(0, 0);
        System.out.println("NO");
    }

    private static void dfs(int count, int index) {
        if (count == WALL_COUNT) {
            if (isPossible()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }
        for (int i = index; i < start.size(); i++) {
            Node node = start.get(i);
            int x = node.x;
            int y = node.y;
            map[x][y] = 'O';
            dfs(count + 1, i + 1);
            map[x][y] = 'X';
        }
    }

    private static boolean isPossible() {
        for (Node node : teacher) {
            int tx = node.x;
            int ty = node.y;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                while (isRange(nx, ny)) {
                    if (map[nx][ny] == 'O') break;
                    if (map[nx][ny] == 'S') return false;
                    if (map[nx][ny] == 'T') break;
                    nx += dx[d];
                    ny += dy[d];
                }
            }
        }
        return true;
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
