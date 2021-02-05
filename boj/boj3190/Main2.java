package boj.boj3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main2 {

    static int N, K, L;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Deque<Snake> snake = new ArrayDeque<>();
    static ArrayList<Info> infoList = new ArrayList<>();

    static class Snake {
        int x, y;

        public Snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Info {
        int time;
        char flag;

        public Info(int time, char flag) {
            this.time = time;
            this.flag = flag;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = stoi(br.readLine());
        K = stoi(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < K; i++) {       // 지도에 사과 표시
            st = new StringTokenizer(br.readLine());
            map[stoi(st.nextToken()) - 1][stoi(st.nextToken()) - 1] = 1;
        }

        L = stoi(br.readLine());
        for (int i = 0; i < L; i++) {       // info 뱀이 이동하는 정보 저장
            st = new StringTokenizer(br.readLine());
            infoList.add(new Info(stoi(st.nextToken()), st.nextToken().charAt(0)));
        }

        snake.addLast(new Snake(0, 0));
        map[0][0] = -1;
        int time = 0;
        int direction = 3;
        int goalTime = infoList.get(0).time;
        char turnFlag = infoList.get(0).flag;
        int infoIdx = 0;
        while (true) {
            if (time == goalTime) {
                infoIdx++;
                direction = turn(turnFlag, direction);
                if (infoIdx < infoList.size()) {
                    Info nextInfo = infoList.get(infoIdx);
                    turnFlag = nextInfo.flag;
                    goalTime = nextInfo.time;
                }
            }
            Snake curSnake = snake.peekLast();  // 머리
            int tx = curSnake.x;
            int ty = curSnake.y;

            int nx = tx + dx[direction];
            int ny = ty + dy[direction];
            time++;
            if (!isRange(nx, ny) || isSnake(nx, ny)) break; // 범위를 벗어나거나, 자신의 몸통을 만나면 종료
            snake.addLast(new Snake(nx, ny));
            if (map[nx][ny] != 1) {     // 사과가 없다면, 꼬리 부분 한칸 이동 -> 꼬리 제거
                Snake tmp = snake.pollFirst();
                map[tmp.x][tmp.y] = 0;
            }
            map[nx][ny] = -1;
        }

        System.out.println(time);

    }

    static int turn(char flag, int d) {       // L(왼쪽으로 90도 회전), D(오른쪽으로 90도 회전)
        int res = 0;
        if (flag == 'L') {
            switch (d) {
                case 0:
                    res = 2;
                    break;
                case 1:
                    res = 3;
                    break;
                case 2:
                    res = 1;
                    break;
                case 3:
                    res = 0;
                    break;
            }
        }
        else {
            switch (d) {
                case 0:
                    res = 3;
                    break;
                case 1:
                    res = 2;
                    break;
                case 2:
                    res = 0;
                    break;
                case 3:
                    res = 1;
                    break;
            }
        }
        return res;
    }

    static boolean isSnake(int x, int y) {
        return map[x][y] == -1 ? true : false;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}

