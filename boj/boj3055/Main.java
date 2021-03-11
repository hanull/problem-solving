package boj.boj3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static boolean isEscape, isDie;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Deque<Node> hedgehogList = new ArrayDeque<>();
    static Deque<Node> waterList = new ArrayDeque<>();
    static Node targetPoint;
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
        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '*') waterList.addFirst(new Node(i, j));
                else if (map[i][j] == 'S') {
                    hedgehogList.addFirst(new Node(i, j));
                    map[i][j] = '.';
                }
                else if (map[i][j] == 'D') targetPoint = new Node(i, j);
            }
        }

        int time = 0;
        while (true) {
            // 물 이동
            spreadWater();

            // 고슴도치 이동
            move();
            if (isEscape) break;
            if (isDie) break;
            time++;
        }
        if (isEscape) {
            System.out.println(time);
        } else {
            System.out.println("KAKTUS");
        }

    }

    static void move() {
        int size = hedgehogList.size();
        for (int t = 0; t < size; t++) {
            Node tmp = hedgehogList.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            if (map[tx][ty] == 'D') {
                isEscape = true;
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == '*' || map[nx][ny] == 'X') continue;
                visited[nx][ny] = true;
                hedgehogList.addLast(new Node(nx, ny));
            }
        }
        if (hedgehogList.size() == 0) {
            isDie = true;
        }
    }

    static void spreadWater() {
        int size = waterList.size();
        for (int t = 0; t < size; t++) {
            Node tmp = waterList.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == '*' || map[nx][ny] == 'D' || map[nx][ny] == 'X') continue;
                map[nx][ny] = '*';
                waterList.addLast(new Node(nx, ny));
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= R || y < 0 || y >= C ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
