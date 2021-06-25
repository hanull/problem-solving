package boj.boj20058;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N, M, SIZE;
    static int[][] iceMap;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        SIZE = (int) Math.pow(2, N);
        iceMap = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                iceMap[i][j] = stoi(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            int range = (int) Math.pow(2, stoi(st.nextToken()));
            if (range == 0) continue;
            int[][] tempMap = new int[SIZE][SIZE];
            for (int i = 0; i <= SIZE - range; i += range) {
                for (int j = 0; j <= SIZE - range; j += range) {
                    turn(i, j, range, tempMap);
                }
            }
            changeMap(tempMap);
            melt();
        }
        System.out.println(getTotalIce());
        System.out.println(findMaxIceBlock());
    }

    private static void changeMap(int[][] tempMap) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                iceMap[i][j] = tempMap[i][j];
            }
        }
    }

    private static void print() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(iceMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int findMaxIceBlock() {
        int maxSize = 0;
        boolean[][] visited = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (visited[i][j] || iceMap[i][j] == 0) continue;
                maxSize = Math.max(maxSize, bfs(i, j, visited));
            }
        }
        return maxSize;
    }

    private static int bfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        int count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int tx = node.x;
            int ty = node.y;
            count++;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (iceMap[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny));
            }
        }
        return count;
    }

    private static int getTotalIce() {
        int total = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                total += iceMap[i][j];
            }
        }
        return total;
    }

    private static void melt() {
        boolean[][] temp = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!checkMelt(i, j)) temp[i][j] = true;
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (temp[i][j] && iceMap[i][j] > 0) iceMap[i][j] -= 1;
            }
        }
    }

    private static boolean checkMelt(int x, int y) {
        int count = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!isRange(nx, ny)) {
                count++;
                continue;
            }
            if (iceMap[nx][ny] == 0) count++;
        }
        return count >= 2 ? false : true;
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE ? false : true;
    }

    private static void turn(int x, int y, int range, int[][] tempMap) {
        for (int j = 0; j < range; j++) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < range; i++) {
                stack.add(iceMap[i + x][j + y]);
            }
            for (int c = 0; c < range; c++) {
                tempMap[j + x][c + y] = stack.pop();
            }
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
