package boj.boj17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, D;
    static int max = 0;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[][] map, mapOrigin;
    static int[] isSelectedArcher = new int[3];
    static int totalMonsterCount;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        D = stoi(st.nextToken());
        map = new int[N + 1][M];
        mapOrigin = new int[N + 1][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                mapOrigin[i][j] = map[i][j];
                if (map[i][j] == 1) totalMonsterCount++;
            }
        }

        selectArcher(0, 0);
        System.out.println(max);
    }

    static void selectArcher(int cnt, int start) {
        if (cnt == 3) {
            copyMap();
            playGame();
            return;
        }

        for (int i = start; i < M; i++) {
            isSelectedArcher[cnt] = i;
            selectArcher(cnt + 1, i + 1);
        }
    }

    static void playGame() {
        int killMonster = 0;
        int leftMonster = totalMonsterCount;

        Deque<Node> dieQue = new ArrayDeque<>();
        while (leftMonster > 0) {
            // 3명의 궁수가 동시에 적 공격
            for (int archerPoint : isSelectedArcher) {

                PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        if (o1.dist == o2.dist) {
                            return Integer.compare(o1.y, o2.y);
                        }
                        return Integer.compare(o1.dist, o2.dist);
                    }
                });

                boolean[][] visited = new boolean[N][M];
                Deque<Node> deq = new ArrayDeque<>();
                deq.add(new Node(N, archerPoint, 0));
                while (!deq.isEmpty()) {    // 가장 가까운 적 공격 (같은 거리일 때, 왼쪽에 있는 적 먼저 죽이기)
                    Node tmp = deq.pollFirst();
                    int tx = tmp.x;
                    int ty = tmp.y;
                    int dist = tmp.dist;
                    if (map[tx][ty] == 1 && dist <= D) {
                        pq.add(tmp);
                    }
                    for (int i = 0; i < 4; i++) {
                        int nx = tx + dx[i];
                        int ny = ty + dy[i];
                        if (!isRange(nx, ny)) continue;
                        if (visited[nx][ny]) continue;
                        visited[nx][ny] = true;
                        deq.add(new Node(nx, ny, dist + 1));
                    }
                }
                if (!pq.isEmpty()) {
                    dieQue.add(pq.poll());
                }

            }

            while (!dieQue.isEmpty()) {     // 죽은 적의 위치 0으로 변경
                Node tmp = dieQue.pollFirst();
                if (map[tmp.x][tmp.y] == 1) {
                    killMonster++;
                    leftMonster--;
                    map[tmp.x][tmp.y] = 0;
                }
            }

            // 남은 적 아래로 한 칸 이동
            for (int i = N - 1; i >= 0; i--) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        if (i == N - 1) {
                            leftMonster--;
                        } else {
                            map[i + 1][j] = 1;
                        }
                        map[i][j] = 0;
                    }
                }
            }

        }

        max = Math.max(max, killMonster);
    }

    static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = mapOrigin[i][j];
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
