package boj.boj19237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Smell {
        int sharkNo, power;

        public Smell(int sharkNo, int power) {
            this.sharkNo = sharkNo;
            this.power = power;
        }
    }
    static class Shark {
        int x, y, direction;

        public Shark(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
    static Smell[][] smellMap;
    static PriorityQueue<Integer> aliveShark = new PriorityQueue<>();
    static Shark[] sharkInfo;
    static int[][][] directionInfo;
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        input();
        int time = 0;
        while (true) {
            if (time > 1000) break;
            if (aliveShark.size() == 1) break;
            moveShark();
            goAwaySmell();
            time++;
        }
        System.out.println(time > 1000 ? -1 : time);
    }

    private static void goAwaySmell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (smellMap[i][j] != null) {
                    if (smellMap[i][j].power == 1) {
                        smellMap[i][j] = null;
                    } else {
                        smellMap[i][j].power -= 1;
                        if (smellMap[i][j].power == K) aliveShark.add(smellMap[i][j].sharkNo);
                    }
                }
            }
        }
    }

    private static void moveShark() {
        int[][] tmpMap = copyMap();
        while (!aliveShark.isEmpty()) {
            int no = aliveShark.poll();
            int tx = sharkInfo[no].x;
            int ty = sharkInfo[no].y;
            int td = sharkInfo[no].direction;
            boolean flag = false;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[directionInfo[no][td][d]];
                int ny = ty + dy[directionInfo[no][td][d]];
                if (!isRange(nx, ny)) continue;
                if (tmpMap[nx][ny] != 0) continue;
                sharkInfo[no].x = nx;
                sharkInfo[no].y = ny;
                sharkInfo[no].direction = directionInfo[no][td][d];
                if (smellMap[nx][ny] == null || smellMap[nx][ny].power != K + 1) smellMap[nx][ny] = new Smell(no, K + 1);
                flag = true;
                break;
            }
            if (!flag) {
                for (int d = 0; d < 4; d++) {
                    int nx = tx + dx[directionInfo[no][td][d]];
                    int ny = ty + dy[directionInfo[no][td][d]];
                    if (!isRange(nx, ny)) continue;
                    if (smellMap[nx][ny].sharkNo != no) continue;
                    sharkInfo[no].x = nx;
                    sharkInfo[no].y = ny;
                    sharkInfo[no].direction = directionInfo[no][td][d];
                    smellMap[nx][ny] = new Smell(no, K + 1);
                    break;
                }
            }
        }
    }

    private static int[][] copyMap() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (smellMap[i][j] != null) tmp[i][j] = smellMap[i][j].power;
            }
        }
        return tmp;
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
    }

    private static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        smellMap = new Smell[N][N];
        sharkInfo = new Shark[M];
        directionInfo = new int[M][4][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = stoi(st.nextToken());
                if (num > 0) {
                    --num;
                    aliveShark.add(num);
                    smellMap[i][j] = new Smell(num, K);
                    sharkInfo[num] = new Shark(i, j, 0);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            sharkInfo[i].direction = stoi(st.nextToken()) - 1;
        }
        for (int t = 0; t < M; t++) {
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    directionInfo[t][i][j] = stoi(st.nextToken()) - 1;
                }
            }
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
