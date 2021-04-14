package SWEA.벽돌깨기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

    static int N, W, H;
    static int[][] map, originMap;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] isSelected;
    static int minLeftBrick;
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
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            W = stoi(st.nextToken());
            H = stoi(st.nextToken());
            map = new int[H][W];
            originMap = new int[H][W];
            isSelected = new int[N];
            minLeftBrick = Integer.MAX_VALUE;

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = stoi(st.nextToken());
                    originMap[i][j] = map[i][j];
                }
            }
            setBall(0);
            result.append("#").append(tc).append(" ").append(minLeftBrick).append("\n");
        }
        System.out.print(result);
    }

    static void setBall(int cnt) {
        if (minLeftBrick == 0) return;
        if (cnt == N) {
            copyMap();
            shoot();
            minLeftBrick = Math.min(minLeftBrick, totalBrick());
            return;
        }

        for (int i = 0; i < W; i++) {
            isSelected[cnt] = i;
            setBall(cnt + 1);
        }
    }

    static void shoot() {
        Deque<Node> deque = null;
        for (int i = 0; i < N; i++) {
            deque = new ArrayDeque<>();
            boolean[][] visited = new boolean[H][W];

            int y = isSelected[i];
            int x = findBrickPoint(y);
            if (x == H) continue;
            deque.add(new Node(x, y));
            visited[x][y] = true;

            while (!deque.isEmpty()) {
                Node tmp = deque.pollFirst();
                int tx = tmp.x;
                int ty = tmp.y;
                int power = map[tx][ty];
                map[tx][ty] = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = tx + dx[d];
                    int ny = ty + dy[d];
                    int count = 1;
                    while (isRange(nx, ny) && count++ < power) {
                        if (map[nx][ny] > 0 && !visited[nx][ny]) {
                            visited[nx][ny] = true;
                            deque.add(new Node(nx, ny));
                        }
                        nx += dx[d];
                        ny += dy[d];
                    }
                }
            }

            dropBrick();

        }

    }

    static void dropBrick() {
        for (int j = 0; j < W; j++) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < H; i++) {
                if (map[i][j] > 0 ) {
                    stack.add(map[i][j]);
                    map[i][j] = 0;
                }
            }
            int idx = H - 1;
            while (!stack.isEmpty()) {
                map[idx][j] = stack.pop();
                idx--;
            }
        }
    }

    static int findBrickPoint(int col) {
        for (int i = 0; i < H; i++) {
            if (map[i][col] > 0) return i;
        }
        return H;
    }

    static int totalBrick() {
        int total = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] > 0) total++;
            }
        }
        return total;
    }

    static void copyMap() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                map[i][j] = originMap[i][j];
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= H || y < 0 || y >= W ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
