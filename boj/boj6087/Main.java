package boj.boj6087;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MAX = 987654321;
    static int N, M, startX, startY;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y, direction, count;

        public Node(int x, int y, int direction, int count) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][4];
        int count = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'C' && count++ == 0) {
                    map[i][j] = '.';
                    startX = i;
                    startY = j;
                }
            }
        }
        System.out.println(solve());

    }

    static int solve() {
        int result = MAX;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.count - o2.count);
        priorityQueue.add(new Node(startX, startY, -1, -1));
        visited[startX][startY][0] = true;

        while (!priorityQueue.isEmpty()) {
            Node tmp = priorityQueue.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int direction = tmp.direction;
            int count = tmp.count;
            if (map[tx][ty] == 'C') {
                result = count;
                break;
            }
            if (direction != -1) {
                if (visited[tx][ty][direction]) continue;
                visited[tx][ty][direction] = true;
            }
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == '*') continue;
                if (direction == d) priorityQueue.add(new Node(nx, ny, d, count));
                else priorityQueue.add(new Node(nx, ny, d, count + 1));
            }
        }

        return result;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
