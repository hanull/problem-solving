package Programmers.행렬테두리회전하기;

import java.util.Arrays;

public class Solution {

    static int[][] map;
    static public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        map = new int[rows][columns];
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = num++;
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int x1 = queries[i][0] - 1;
            int y1 = queries[i][1] - 1;
            int x2 = queries[i][2] - 1;
            int y2 = queries[i][3] - 1;
            int min = rotate(x1, y1, x2, y2);
            answer[i] = min;
        }
        return answer;
    }

    private static int rotate(int x1, int y1, int x2, int y2) {
        int temp = map[x1][y1];
        int min = temp;
        for (int i = x1 + 1; i <= x2; i++) {
            map[i - 1][y1] = map[i][y1];
            min = Math.min(min, map[i][y1]);
        }
        for (int j = y1 + 1; j <= y2; j++) {
            map[x2][j - 1] = map[x2][j];
            min = Math.min(min, map[x2][j]);
        }
        for (int i = x2 - 1; i >= x1; i--) {
            map[i + 1][y2] = map[i][y2];
            min = Math.min(min, map[i][y2]);
        }
        for (int j = y2 - 1; j >= y1; j--) {
            map[x1][j+1] = map[x1][j];
            min = Math.min(min, map[x1][j]);
        }
        map[x1][y1 + 1] = temp;
//        for (int i = x1; i <= x2; i++) {
//            for (int j = y1; j <= y2; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        return min;
    }

    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        System.out.println(Arrays.toString(solution(rows, columns, queries)));
    }
}
