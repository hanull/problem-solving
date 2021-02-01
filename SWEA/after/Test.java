package SWEA.after;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Test {

    static int T, N;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int homeCount = 0;
    static ArrayList<Node> list = new ArrayList<>();

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = stoi(br.readLine());
        N = stoi(br.readLine());
        map = new char[N][N];

        for (int tc = 1; tc <= T; tc++) {
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 'H') homeCount++;
                    else if (map[i][j] == 'A' || map[i][j] == 'B' || map[i][j] == 'C') list.add(new Node(i, j));
                }
            }
            findHome();
            System.out.println("#"+ tc + " " +homeCount);
        }
    }

    static void findHome() {
        for (Node node : list) {
            int tx = node.x;
            int ty = node.y;

            int cover = map[tx][ty] - 'A' + 1;
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= cover; j++) {
                    int nx = tx + dx[i] * j;
                    int ny = ty + dy[i] * j;
                    if (!isRange(nx, ny)) continue;
                    if (map[nx][ny] == 'X') continue;
                    if (map[nx][ny] == 'H') {
                        homeCount--;
                        map[nx][ny] = 'X';
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
