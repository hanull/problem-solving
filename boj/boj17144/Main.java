package boj.boj17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, M, T;
    static int[][] map;
    static Node airCleanerA, airCleanerB;
    static Deque<Node> microDustList;
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
        T = stoi(st.nextToken());
        map = new int[N][M];
        int count = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] == -1) {
                    if (count == 0) {
                        airCleanerA = new Node(i, j);
                        count++;
                    } else {
                        airCleanerB = new Node(i, j);
                    }
                }
            }
        }

        int time = 0;
        while (time++ < T) {
            // 미세먼지 확산
            spreadMicroDust();
            // 공기청정기 작동
            turnOnAirCleaner();
        }
        System.out.println(calculateTotalMicroDust());

    }

    static int calculateTotalMicroDust() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) result += map[i][j];
            }
        }
        return result;
    }

    static void turnOnAirCleaner() {
        // 반시계 방향
        int x = airCleanerA.x;
        int y = airCleanerA.y;
        for (int i = x - 1; i >= 0; i--) {
            map[i + 1][y] = map[i][y];
        }
        for (int j = y + 1; j < M; j++) {
            map[0][j - 1] = map[0][j];
        }
        for (int i = 1; i <= x; i++) {
            map[i - 1][M - 1] = map[i][M - 1];
        }
        for (int j = M - 2; j > y; j--) {
            map[x][j + 1] = map[x][j];
        }
        map[x][y + 1] = 0;
        map[x][y] = -1;

        // 시계 방향
        x = airCleanerB.x;
        for (int i = x + 1; i < N; i++) {
            map[i - 1][y] = map[i][y];
        }
        for (int j = 1; j < M; j++) {
            map[N - 1][j - 1] = map[N - 1][j];
        }
        for (int i = N - 2; i >= x; i--) {
            map[i + 1][M - 1] = map[i][M - 1];
        }
        for (int j = M - 2; j >= y; j--) {
            map[x][j + 1] = map[x][j];
        }
        map[x][y + 1] = 0;
        map[x][y] = -1;
    }

    static void spreadMicroDust() {
        findMicroDust();
        int[][] tempArray = new int[N][M];
        while (!microDustList.isEmpty()) {
            Node tmp = microDustList.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            int plusMicroDust = map[tx][ty] / 5;
            int count = 0;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == -1) continue;
                count++;
                tempArray[nx][ny] += plusMicroDust;
            }
            tempArray[tx][ty] -= plusMicroDust * count;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] += tempArray[i][j];
            }
        }

    }

    static void findMicroDust() {
        microDustList = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) microDustList.add(new Node(i, j));
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
