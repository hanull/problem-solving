package SWEA.탈주범검거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {

    static int N, M, R, C, L;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder result = new StringBuilder();
        int T = stoi(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());
            R = stoi(st.nextToken());
            C = stoi(st.nextToken());
            L = stoi(st.nextToken());
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = stoi(st.nextToken());
                }
            }

            int totalCount = getTotalCount();
            result.append("#").append(tc).append(" ").append(totalCount).append("\n");
        }
        System.out.print(result);
    }

    static int getTotalCount() {
        Deque<Node> deque = new ArrayDeque<>();
        visited = new boolean[N][M];
        deque.add(new Node(R, C, 1));
        visited[R][C] = true;
        int total = 0;

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            int time = tmp.time;
            if (time <= L) total++;
            else continue;
            int[] direction = getDirection(map[tx][ty]);
            for (int d : direction) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (!isPossible(d, map[nx][ny])) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                deque.add(new Node(nx, ny, time + 1));
            }
        }

        return total;
    }

    static boolean isPossible(int currentDirection, int nextDirection) {
        if (currentDirection == 0) {
            if (nextDirection == 3 || nextDirection == 4|| nextDirection == 7) return false;
        } else if (currentDirection == 1) {
            if (nextDirection == 3 || nextDirection == 5|| nextDirection == 6) return false;
        } else if (currentDirection == 2) {
            if (nextDirection == 2 || nextDirection == 6|| nextDirection == 7) return false;
        } else if (currentDirection == 3) {
            if (nextDirection == 2 || nextDirection == 4|| nextDirection == 5) return false;
        }
        return true;
    }

    static int[] getDirection(int type) {
        int[] res = {0, 1, 2, 3};
        if (type != 1) {
            res = new int[2];
        }

        switch (type) {
            case 2:
                res[0] = 0;
                res[1] = 1;
                break;
            case 3:
                res[0] = 2;
                res[1] = 3;
                break;
            case 4:
                res[0] = 0;
                res[1] = 3;
                break;
            case 5:
                res[0] = 1;
                res[1] = 3;
                break;
            case 6:
                res[0] = 1;
                res[1] = 2;
                break;
            case 7:
                res[0] = 0;
                res[1] = 2;
                break;
        }
        return res;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
