package boj.boj3190;

import java.io.*;
import java.util.*;

public class Main5 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class ChangeInformation {
        int changeTime;
        String turnDirection;

        public ChangeInformation(final int changeTime, final String turnDirection) {
            this.changeTime = changeTime;
            this.turnDirection = turnDirection;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        boolean[][] apples = new boolean[N][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            apples[x][y] = true;
        }
        int L = Integer.parseInt(br.readLine());
        Queue<ChangeInformation> changeInfos = new ArrayDeque<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            changeInfos.add(new ChangeInformation(time, direction));
        }

        boolean[][] used = new boolean[N][N];
        Deque<Node> snake = new ArrayDeque<>();
        snake.add(new Node(0, 0));
        used[0][0] = true;
        int answer = 0;
        int direction = 3;
        while (true) {
            Node current = snake.peekLast();
            int x = current.x;
            int y = current.y;

            // 머리 이동
            answer++;
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            // 벽, 몸이라면 종료
            if (isOutOfRange(nx, ny, N) || used[nx][ny]) {
                break;
            }

            // 이동하는 곳에 사과가 없다면, 꼬리 제거
            snake.addLast(new Node(nx, ny));
            used[nx][ny] = true;
            if (!apples[nx][ny]) {
                Node tail = snake.pollFirst();
                used[tail.x][tail.y] = false;
            } else {
                apples[nx][ny] = false;
            }

            // 게임 시간이 X초가 됐다면, 방향 회전
            if (!changeInfos.isEmpty() && answer == changeInfos.peek().changeTime) {
                direction = turn(direction, changeInfos.peek().turnDirection);
                changeInfos.poll();
            }
        }
        System.out.println(answer);
    }

    private static boolean isOutOfRange(final int x, final int y, final int n) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }

    private static int turn(final int current, final String direction) {
        if (direction.equals("D")) {
            if (current == 0) {
                return 3;
            } else if (current == 1) {
                return 2;
            } else if (current == 2) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (current == 0) {
                return 2;
            } else if (current == 1) {
                return 3;
            } else if (current == 2) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
