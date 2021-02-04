package boj.boj2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int totalCheeseCount;
    static Queue<Node> cheeseList = new LinkedList<>();
    static Queue<Node> tmpList = new LinkedList<>();
    static HashMap<Integer, Integer> leftCheeseCount = new HashMap<>();
    static int time = 1;

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
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] == 1) totalCheeseCount++;
            }
        }

        leftCheeseCount.put(0, totalCheeseCount);
        while (totalCheeseCount > 0) {
            findCheese();
            melting();
            leftCheeseCount.put(time, totalCheeseCount);
            time++;
        }

        System.out.println(--time);
        System.out.println(leftCheeseCount.get(time - 1));
    }

    static void melting() {
        totalCheeseCount -= cheeseList.size();
        while (!cheeseList.isEmpty()) {
            Node tmp = cheeseList.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            tmpList.add(tmp);
            map[tx][ty] = 0;
        }

    }

    static void findCheese() {
        tmpList.add(new Node(0, 0));
        visited[0][0] = true;
        while (!tmpList.isEmpty()) {
            Node tmp = tmpList.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if (map[nx][ny] == 1) {
                    cheeseList.add(new Node(nx, ny));
                }
                if (map[nx][ny] == 0) {
                    tmpList.add(new Node(nx, ny));
                }
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
