package boj.boj2665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N;
    static int minChangedRoom = 2500;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y, count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = input[j] - '0';
            }
        }
        move();
        System.out.println(minChangedRoom);
    }

    private static void move() {
        Queue<Node> queue = new LinkedList<>();
        int[][] countArray = new int[N][N];
        queue.add(new Node(0, 0, 0));
        for (int i = 0; i < N; i++) {
            Arrays.fill(countArray[i], 2500);
        }

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int count = tmp.count;
            if (tx == N - 1 && ty == N - 1) {
                minChangedRoom = Math.min(minChangedRoom, count);
            }
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == 0) { // 검은방
                    if (count + 1 < countArray[nx][ny]) {
                        countArray[nx][ny] = count + 1;
                        queue.add(new Node(nx, ny, count + 1));
                    }
                } else {    // 흰방
                    if (count < countArray[nx][ny]) {
                        countArray[nx][ny] = count;
                        queue.add(new Node(nx, ny, count));
                    }
                }
            }
        }
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
