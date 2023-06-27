package Programmers.파괴되지않은건물;

public class Solution {

    public int solution(int[][] board, int[][] skill) {
        int[][] changeMap = new int[board.length][board[0].length];
        for (int[] temp : skill) {
            int type = temp[0];
            int r1 = temp[1];
            int c1 = temp[2];
            int r2 = temp[3];
            int c2 = temp[4];
            int degree = temp[5];
            if (type == 1) {
                degree *= -1;
            }
            changeMap[r1][c1] += degree;
            if (c2 + 1 < changeMap[0].length) {
                changeMap[r1][c2 + 1] += degree * -1;
            }
            if (r2 + 1 < changeMap.length) {
                changeMap[r2 + 1][c1] += degree * -1;
            }
            if (r2 + 1 < changeMap.length && c2 + 1 < changeMap[0].length) {
                changeMap[r2 + 1][c2 + 1] += degree;
            }
        }

        for (int i = 0; i < changeMap.length; i++) {
            for (int j = 1; j < changeMap[0].length; j++) {
                changeMap[i][j] += changeMap[i][j - 1];
            }
        }
        for (int i = 0; i < changeMap[0].length; i++) {
            for (int j = 1; j < changeMap.length; j++) {
                changeMap[j][i] += changeMap[j - 1][i];
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += changeMap[i][j];
            }
        }

        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}},
                new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}}));
        System.out.println(sol.solution(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                new int[][]{{1, 1, 1, 2, 2, 4}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}}));
    }
}
