package boj.boj14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {

    static int N, M, pointX, pointY, K;
    static int[][] map;
    static int[] commands;
    static List<Integer> answerList = new ArrayList<>();
    static int[] dx = {0, 0, -1, 1};   // 동, 서, 북, 남
    static int[] dy = {1, -1, 0, 0};
    static int[] dice = new int[6];

    public static void main(String[] args) throws IOException {
        init();
        move();
        print();
    }

    private static void move() {
        for (int command : commands) {
            int nx = pointX + dx[command];
            int ny = pointY + dy[command];
            if (!isRange(nx, ny)) continue;
            if (command == 0) { // 동
                int temp = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[2];
                dice[2] = temp;
            } else if (command == 1) {  // 서
                int temp = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[1];
                dice[1] = temp;
            } else if (command == 2) {  // 북
                int temp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[3];
                dice[3] = temp;
            } else {    // 남
                int temp = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[4];
                dice[4] = temp;
            }
            if (map[nx][ny] == 0) {
                map[nx][ny] = dice[5];
            } else {
                dice[5] = map[nx][ny];
                map[nx][ny] = 0;
            }
            pointX = nx;
            pointY = ny;
            answerList.add(dice[0]);
        }
    }

    private static void print() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < answerList.size(); i++) {
            answer.append(answerList.get(i));
            if (i < answerList.size() - 1) answer.append("\n");
        }
        System.out.print(answer);
    }

    private static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        pointX = stoi(st.nextToken());
        pointY = stoi(st.nextToken());
        K = stoi(st.nextToken());
        map = new int[N][M];
        commands = new int[K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            commands[i] = stoi(st.nextToken()) - 1;
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
