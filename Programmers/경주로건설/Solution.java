package Programmers.경주로건설;

import java.util.*;

public class Solution {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;

    public int solution(int[][] board) {
        // 이미 방문한 정점이라도 어느 방향에서 왔느냐에 따라서 최소 비용이 달라진다.
        // 따라서 3차원 배열을 사용하여 비용을 갱신해야 한다.
        int[][][] cost = new int[board.length][board.length][4];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }
        for (int d = 0; d < 4; d++) {
            cost[0][0][d] = 0;
        }
        move(0, 0, -1, 0, board, cost);
        return answer;
    }

    private void move(final int x, final int y, final int direction, final int total, final int[][] board,
                      final int[][][] cost) {
        // 현재 정점까지의 비용이 이미 최소 비용(answer)보다 크다면, 더이상 확인할 필요 없음.
        if (total > answer) {
            return;
        }
        if (x == board.length - 1 && y == board.length - 1) {
            answer = total;
            return;
        }
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= board.length || ny >= board.length || nx < 0 || ny < 0) {
                continue;
            }
            if (board[nx][ny] == 1) {
                continue;
            }
            int nextTotal = total + 100;
            if (direction != -1 && d != direction) {
                nextTotal += 500;
            }
            if (nextTotal < cost[nx][ny][d]) {
                cost[nx][ny][d] = nextTotal;
                move(nx, ny, d, nextTotal, board, cost);
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
//        System.out.println(sol.solution(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println(sol.solution(new int[][]{{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}}));
    }
}
