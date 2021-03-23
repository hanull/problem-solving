package boj.boj4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static final int MAX = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] cost;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y, money;

        public Node(int x, int y, int money) {
            this.x = x;
            this.y = y;
            this.money = money;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder result = new StringBuilder();
        int testcase = 1;
        while (true) {
            N = stoi(br.readLine());
            if (N == 0) break;
            map = new int[N][N];
            cost = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                Arrays.fill(cost[i], MAX);
                for (int j = 0; j < N; j++) {
                    map[i][j] = stoi(st.nextToken());
                }
            }
            move(0, 0);
            result.append("Problem ").append(testcase).append(": ").append(cost[N - 1][N - 1]).append("\n");
            testcase++;
        }
        System.out.print(result);
    }

    static void move(int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> o1.money - o2.money));
        pq.add(new Node(x, y, map[x][y]));
        cost[0][0] = map[x][y];

        while (!pq.isEmpty()) {
            Node tmp = pq.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int currentMoney = tmp.money;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (cost[nx][ny] > currentMoney + map[nx][ny]) {
                    cost[nx][ny] = currentMoney + map[nx][ny];
                    pq.add(new Node(nx, ny, cost[nx][ny]));
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
