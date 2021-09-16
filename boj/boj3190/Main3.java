package boj.boj3190;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main3 {

    static int N, headX, headY, headD;
    static int[][] map;
    static Command[] commands;
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};
    static int[] turnRight = {1, 2, 3, 0};
    static int[] turnLeft = {3, 0, 1, 2};
    static Deque<Snake> snakes = new ArrayDeque<>();
    static class Snake {
        int x, y;

        public Snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Command {
        int time;
        char direction;

        public Command(int time, char direction) {
            this.time = time;
            this.direction = direction;
        }
    }

    public static void main(String[] args) {
        init();
        int answer = 0;
        int targetNo = 0;
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        while (true) {
            // 방향 바꿀 시간이 되었는지 체크
            // 시간이 되었으면, 방향 변경
            if (answer == commands[targetNo].time) {
                if (commands[targetNo].direction == 'D') {
                    headD = turnRight[headD];
                } else {
                    headD = turnLeft[headD];
                }
                if (targetNo + 1 < commands.length) {
                    targetNo++;
                }
            }

            // 뱀 이동
            // 범위를 벗어나거나, 몸통에 부딪히면 종료
            answer++;
            int nx = headX + dx[headD];
            int ny = headY + dy[headD];
            if (!isRange(nx, ny)) break;
            if (visited[nx][ny]) break;
            snakes.addLast(new Snake(nx, ny));
            visited[nx][ny] = true;
            headX = nx;
            headY = ny;

            // 이동한 위치에 사과가 있는지 체크
            // 사과가 있을 경우, 뱀의 꼬리 유지 ==> head 변경
            // 사과가 없을 경우, 뱀의 꼬리 이동 ==> 덱 pollFirst()
            if (map[nx][ny] == 1) {
                map[nx][ny] = 0;
            } else {
                Snake snake = snakes.pollFirst();
                if (snake.x == 0 && snake.y == 0) {
                }
                visited[snake.x][snake.y] = false;
            }
        }
        System.out.println(answer);
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        int apple = sc.nextInt();
        for (int i = 0; i < apple; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            map[x][y] = 1;
        }
        int L = sc.nextInt();
        commands = new Command[L];
        for (int i = 0; i < L; i++) {
            int time = sc.nextInt();
            char direction = sc.next().charAt(0);
            commands[i] = new Command(time, direction);
        }
        snakes.addLast(new Snake(0, 0));
    }
}
