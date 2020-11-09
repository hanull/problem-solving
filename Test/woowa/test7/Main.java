package Test.woowa.test7;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] answer = sol.solution(4, true);
        for (int[] tmp : answer) {
            System.out.println(Arrays.toString(tmp));
        }
    }
}

class Solution {

    static int[] dx;
    static int[] dy;
    static int[][] map;
    static int x = 0;
    static int y = 0;
    static int direction = 0;
    static int time = 0;

    public int[][] solution(int n, boolean horizontal) {
        init(n, horizontal);
        map[0][0] = 0;
        while (true) {
            if (x == n - 1 && y == n - 1) break;
            move(n);
            turn();
        }
        return map;
    }

    private void turn() {
        if (direction == 3) {
            direction = 0;
        } else {
            direction += 1;
        }
    }

    private void move(int n) {
        if (direction == 0 || direction == 2) { // 오른쪽 or 아래로 이동할 경우, 한 칸 이동   시간 + 1초씩
            x += dx[direction];
            y += dy[direction];
            if (!isRange(x, y, n)) {    // 해당 방향으로 이동 못하면, 방향 전환
                int d = direction;
                x -= dx[d];
                y -= dy[d];
                d = d == 0 ? 2 : 0;
                x += dx[d];
                y += dy[d];
            }
            time += 1;
            map[x][y] = time;
        } else {    // 대각선 이동 시, 더 이상 이동 불가할 때 까지   시간 + 2초씩
            while (true) {
                x += dx[direction];
                y += dy[direction];
                if (isRange(x, y, n)) {
                    time += 2;
                    map[x][y] = time;
                } else {
                    x -= dx[direction];
                    y -= dy[direction];
                    break;
                }
            }
        }
    }

    private boolean isRange(int x, int y, int n) {
        if (x < 0 || x >= n || y < 0 || y >= n) {
            return false;
        }
        return true;
    }

    private void init(int n, boolean horizontal) {
        map = new int[n][n];

        if (horizontal) {
            // 오른쩍, 대각선 왼쪽 아래, 아래, 대각선 오른쪽 위
            dx = new int[]{0, 1, 1, -1};
            dy = new int[]{1, -1, 0, 1};
        } else {
            // 아래, 대각선 오른쪽 위, 오른쪽, 대각선 왼쪽 아래
            dx = new int[]{1, -1, 0, 1};
            dy = new int[]{0, 1, 1, -1};
        }
    }
}