package boj.boj2411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, I, B;
    static int[][] map;
    static int[][] dp;
    static Node[] items;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        if (map[1][1] == -1 || map[N][M] == -1) {
            System.out.println(0);
        } else {
            dp[1][0] = 1;
            int x = 1, y = 1;
            for (int index = 0; index <= I; index++) {
                int goalX = items[index].x;
                int goalY = items[index].y;
                for (int i = x; i <= goalX; i++) {
                    for (int j = y; j <= goalY; j++) {
                        if (map[i][j] == -1) {
                            dp[i][j] = 0;
                        } else if (map[i - 1][j] != -1 && map[i][j - 1] != -1) {
                            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                        } else if (map[i - 1][j] != -1) {
                            dp[i][j] = dp[i - 1][j];
                        } else {
                            dp[i][j] = dp[i][j - 1];
                        }
                    }
                }
                x = goalX;
                y = goalY;
            }
            System.out.println(dp[N][M]);
        }
    }


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        I = stoi(st.nextToken());
        B = stoi(st.nextToken());
        map = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];
        items = new Node[I + 1];
        for (int i = 0; i < I; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            map[x][y] = 1;
            items[i] = new Node(x, y);
        }
        items[I] = new Node(N, M);
        Arrays.sort(items, (o1, o2) -> {
            if (o1.x == o2.x) {
                return o1.y - o2.y;
            }
            return o1.x - o2.x;
        });
        for (int i = 0; i < B; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            map[x][y] = -1;
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
