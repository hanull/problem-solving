package boj.boj2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main3 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Deque<Node> airList = new ArrayDeque<>();
    static List<Integer> remainingCheese = new ArrayList<>();
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
            }
        }

        airList.add(new Node(0, 0));
        int time = 0;
        while (true) {
            // 남은 치즈 수 확인
            int leftCheese = findCheese();
            remainingCheese.add(leftCheese);
            if (leftCheese == 0) break;

            Deque<Node> meltingCheeseList = findMeltingCheese();
            while (!meltingCheeseList.isEmpty()) {
                Node tmp = meltingCheeseList.poll();
                int x = tmp.x;
                int y = tmp.y;
                map[x][y] = 0;
            }
            time++;
        }
        System.out.println(time);
        System.out.println(remainingCheese.get(time - 1));
    }

    static Deque<Node> findMeltingCheese() {
        Deque<Node> meltingCheeseList = new ArrayDeque<>();

        while (!airList.isEmpty()) {
            Node tmp = airList.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if (map[nx][ny] == 1) meltingCheeseList.add(new Node(nx, ny));
                else airList.add(new Node(nx, ny));
            }
        }
        airList.addAll(meltingCheeseList);
        return meltingCheeseList;
    }

    static int findCheese() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
