package boj.boj5427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean isEscape, isDie;
    static Deque<Node> fireList = new ArrayDeque<>();
    static Deque<Node> runner = new ArrayDeque<>();
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
        int T = stoi(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            M = stoi(st.nextToken());
            N = stoi(st.nextToken());
            map = new char[N][M];
            visited = new boolean[N][M];
            runner.clear();
            fireList.clear();
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '@') {
                        runner.add(new Node(i, j));
                        visited[i][j] = true;
                        map[i][j] = '.';
                    } else if (map[i][j] == '*') fireList.add(new Node(i, j));
                }
            }

            isEscape = isDie = false;
            int time = 0;
            while (true) {
                spreadFire();
                move();

                if (isDie) {
                    isEscape = false;
                    break;
                }
                time++;

                if (isEscape) break;
            }

            if (isEscape) {
                System.out.println(time);
            } else {
                System.out.println("IMPOSSIBLE");
            }

        }


    }

    static void move() {
        int size = runner.size();
        for (int t = 0; t < size; t++) {
            Node tmp = runner.pollFirst();
            int x = tmp.x;
            int y = tmp.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!isRange(nx, ny)) {
                    isEscape = true;
                    return;
                }
                if (map[nx][ny] == '*' || map[nx][ny] == '#') continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                runner.add(new Node(nx, ny));
            }
        }
        if (runner.size() == 0) {
            isDie = true;
        }
    }

    static void spreadFire() {
        int size = fireList.size();
        for (int t = 0; t < size; t++) {
            Node fire = fireList.pollFirst();
            int x = fire.x;
            int y = fire.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == '*' || map[nx][ny] == '#') continue;
                map[nx][ny] = '*';
                fireList.add(new Node(nx, ny));
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
