package boj.boj20056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] map;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] changeDirection = {{0, 2, 4, 6}, {1, 3, 5, 7}};
    static Queue<FireBall> fireBallList = new LinkedList<>();
    static class FireBall{
        int x, y, weight, speed, direction;

        public FireBall(int x, int y, int weight, int speed, int direction) {
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.speed = speed;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken()) - 1;
            int y = stoi(st.nextToken()) - 1;
            int weight = stoi(st.nextToken());
            int speed = stoi(st.nextToken());
            int direction = stoi(st.nextToken());
            fireBallList.add(new FireBall(x, y, weight, speed, direction));
        }
        while (K-- > 0) {
            // 각 좌표의 파이어볼 개수 초기화
            initMap();
            // 파이어볼 이동
            moveFireBall();
        }
        int totalWeight = 0;
        while (!fireBallList.isEmpty()) {
            totalWeight += fireBallList.poll().weight;
        }
        System.out.println(totalWeight);
    }

    private static void moveFireBall() {
        PriorityQueue<FireBall> movedFireBall = new PriorityQueue<>((o1, o2) -> {
            if (o1.x == o2.x) {
                return o1.y - o2.y;
            }
            return o1.x - o2.x;
        });
        while (!fireBallList.isEmpty()) {
            FireBall fireBall = fireBallList.poll();
            int x = fireBall.x;
            int y = fireBall.y;
            int weight = fireBall.weight;
            int speed = fireBall.speed;
            int direction = fireBall.direction;
            int nx = x + dx[direction] * (speed % N);
            int ny = y + dy[direction] * (speed % N);
            if (!isRange(nx, ny)) { // 맵 범위를 벗어나는 경우,
                nx = outOfMap(nx);
                ny = outOfMap(ny);
            }
            map[nx][ny]++;  // (nx, ny) 위치의 파이어볼 개수++
            movedFireBall.add(new FireBall(nx, ny, weight, speed, direction));
        }
        while (!movedFireBall.isEmpty()) {
            int x = movedFireBall.peek().x;
            int y = movedFireBall.peek().y;
            if (map[x][y] >= 2) { // 현재 위치에 2개 이상의 파이어볼 존재 할 때,
                int count = map[x][y];
                int totalWeight = 0;
                int totalSpeed = 0;
                int oddDirection = 0;
                int evenDirection = 0;
                for (int i = 0; i < count; i++) {
                    FireBall fireBall = movedFireBall.poll();
                    totalWeight += fireBall.weight;
                    totalSpeed += fireBall.speed;
                    if (fireBall.direction % 2 == 0) evenDirection++;
                    else oddDirection++;
                }
                int nextDirection = 0;
                if (oddDirection != 0 && evenDirection != 0) nextDirection = 1;
                if (totalWeight / 5 == 0) continue;
                for (int i = 0; i < 4; i++) {   // 4개의 파이어볼로 나뉨
                    fireBallList.add(new FireBall(x, y, totalWeight / 5, totalSpeed / count, changeDirection[nextDirection][i]));
                }
            } else {
                fireBallList.add(movedFireBall.poll());
            }
        }
    }

    private static int outOfMap(int point) {
        if (point < 0) return N + point;
        if (point >= N) return point - N;
        return point;
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
    }

    private static void initMap() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], 0);
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
