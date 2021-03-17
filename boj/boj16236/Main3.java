package boj.boj16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main3 {

    static int N;
    static int[][] map;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static PriorityQueue<Node> fishList = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.dist == o2.dist) {
                if (o1.x == o2.x) {
                    return o1.y - o2.y;
                }
                return o1.x - o2.x;
            }
            return o1.dist - o2.dist;
        }
    });
    static Shark shark;
    static class Shark {
        Node point;
        int size, eaten;

        public Shark(Node point, int size, int eaten) {
            this.point = point;
            this.size = size;
            this.eaten = eaten;
        }
    }
    static class Node {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

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
                    shark = new Shark(new Node(i, j, 0), 2, 0);
                    map[i][j] = 0;
                }
            }
        }

        int time = 0;
        while (true) {
            // 먹을 수 있는 물고기 찾기
            findFish(shark.point.x, shark.point.y, shark.size);
            // 먹을 수 있는 물고기가 없을 경우, 종료
            if (fishList.size() == 0) break;

            Node fish = fishList.poll();
            map[fish.x][fish.y] = 0;
            shark.point.x = fish.x;
            shark.point.y = fish.y;
            shark.eaten++;

            if (shark.eaten == shark.size) {
                shark.size++;
                shark.eaten = 0;
            }
            time += fish.dist;
        }
        System.out.println(time);

    }

    static void findFish(int x, int y, int sharkSize) {
        fishList.clear();
        Deque<Node> deque = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        deque.add(new Node(x, y, 0));
        visited[x][y] = true;

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
                if (map[nx][ny] > sharkSize) continue;
                visited[nx][ny] = true;
                deque.add(new Node(nx, ny, dist + 1));
                if (map[nx][ny] > 0 && map[nx][ny] < sharkSize)
                    fishList.add(new Node(nx, ny, dist + 1));
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
