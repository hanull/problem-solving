package boj.boj16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main4 {

    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Node shark;
    static class Node {
        int x, y, size, count, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        public Node(int x, int y, int size, int count) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.count = count;
        }
    }
    static PriorityQueue<Node> fishList = new PriorityQueue<>((o1, o2) -> {
        if (o1.dist == o2.dist) {
            if (o1.x == o2.x) {
                return o1.y - o2.y;
            } else {
                return o1.x - o2.x;
            }
        } else {
            return o1.dist - o2.dist;
        }
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Node(i, j, 2, 0);
                    map[i][j] = 0;
                }
            }
        }

        int time = 0;

        while (true) {
            searchFish();
            if (fishList.size() == 0) break;
            Node targetFish = fishList.poll();
            map[targetFish.x][targetFish.y] = 0;
            shark.x = targetFish.x;
            shark.y = targetFish.y;
            shark.count++;
            if (shark.count == shark.size) {
                shark.count = 0;
                shark.size++;
            }
            time += targetFish.dist;
        }
        System.out.println(time);
    }

    private static void searchFish() {
        fishList.clear();
        Deque<Node> deque = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        deque.add(new Node(shark.x, shark.y, 0));
        visited[shark.x][shark.y] = true;

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            int dist = tmp.dist;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] > shark.size) continue;
                visited[nx][ny] = true;
                deque.add(new Node(nx, ny, dist + 1));
                if (map[nx][ny] > 0 && map[nx][ny] < shark.size)
                    fishList.add(new Node(nx, ny, dist + 1));
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
