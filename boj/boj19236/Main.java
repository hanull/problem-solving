package boj.boj19236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
7 6 2 6 15 7 9 3
3 5 1 4 14 1 10 6
6 4 13 3 4 6 11 1
16 5 8 7 5 2 12 2
 */
public class Main {

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};  //  ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[][] basicDirection = {
            {0, 1, 2, 3, 4, 5, 6, 7},
            {1, 2, 3, 4, 5, 6, 7, 0},
            {2, 3, 4, 5, 6, 7, 0, 1},
            {3, 4, 5, 6, 7, 0, 1, 2},
            {4, 5, 6, 7, 0, 1, 2, 3},
            {5, 6, 7, 0, 1, 2, 3, 4},
            {6, 7, 0, 1, 2, 3, 4, 5},
            {7, 0, 1, 2, 3, 4, 5, 6},
    };
    static int answer;
    static Node[] fishInfo = new Node[17];
    static class Node {
        int x, y, direction;

        public Node(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
    static int[][] map = new int[4][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int no = stoi(st.nextToken());
                int d = stoi(st.nextToken()) - 1;
                map[i][j] = no;
                fishInfo[no] = new Node(i, j, d);
            }
        }
        int startNo = map[0][0];
        map[0][0] = 0;
        go(0, 0, fishInfo[startNo].direction, startNo);
        System.out.println(answer);
    }

    private static void go(int sharkX, int sharkY, int direction, int total) {
        if (total > answer) answer = total;
        int[][] tempMap = copyMap();
        Node[] tempFish = copyFish();
        moveFish(sharkX, sharkY);

        for (int i = 1; i <= 3; i++) {
            int nx = sharkX + dx[direction] * i;
            int ny = sharkY + dy[direction] * i;
            if (!isRange(nx, ny)) break;
            if (map[nx][ny] == 0) continue;
            int no = map[nx][ny];
            map[nx][ny] = 0;
            go(nx, ny, fishInfo[no].direction, total + no);
            map[nx][ny] = no;
        }
        for (int i = 0; i < 4; i++) map[i] = tempMap[i].clone();
        for (int i = 1; i < 17; i++) fishInfo[i] = tempFish[i];
    }

    private static Node[] copyFish() {
        Node[] copy = new Node[17];
        for (int i = 1; i < 17; i++) {
            copy[i] = new Node(fishInfo[i].x, fishInfo[i].y, fishInfo[i].direction);
        }
        return copy;
    }

    private static int[][] copyMap() {
        int[][] copy = new int[4][4];
        for (int i = 0; i < 4; i++) {
            copy[i] = map[i].clone();
        }
        return copy;
    }

    private static void moveFish(int sharkX, int sharkY) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] != 0) priorityQueue.add(map[i][j]);
            }
        }
        while (!priorityQueue.isEmpty()) {
            int no = priorityQueue.poll();
            int tx = fishInfo[no].x;
            int ty = fishInfo[no].y;
            int currentDirection = fishInfo[no].direction;
            for (int d = 0; d < 7; d++) {
                int nx = tx + dx[basicDirection[currentDirection][d]];
                int ny = ty + dy[basicDirection[currentDirection][d]];
                fishInfo[no].direction = basicDirection[currentDirection][d];
                if (!isRange(nx, ny) || (nx == sharkX && ny == sharkY)) continue;
                int changingNo = map[nx][ny];
                map[nx][ny] = no;
                map[tx][ty] = changingNo;
                fishInfo[no].x = nx;
                fishInfo[no].y = ny;
                if (changingNo > 0) {
                    fishInfo[changingNo].x = tx;
                    fishInfo[changingNo].y = ty;
                }
                break;
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x < 0 || x >= 4 || y < 0 || y >= 4 ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }


}
