package boj.boj7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int unripeTomato;
    static Queue<Node> tomato = new LinkedList<>();
    static class Node {
        int x, y, day;

        public Node(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] == 1) tomato.add(new Node(i, j, 0));
                else if (map[i][j] == 0) unripeTomato++;
            }
        }

        spreadTomato();

    }

    static void spreadTomato() {
        int totalDay = 0;
        while (!tomato.isEmpty()) {
            Node tmp = tomato.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int day = tmp.day;
            totalDay = day;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == 1 || map[nx][ny] == -1) continue;
                map[nx][ny] = 1;
                unripeTomato--;
                tomato.add(new Node(nx, ny, day + 1));
            }
        }

        if (unripeTomato == 0) System.out.println(totalDay);
        else System.out.println(-1);

    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
