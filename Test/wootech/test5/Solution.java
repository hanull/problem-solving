package Test.wootech.test5;

public class Solution {

    public int[][] solution(int rows, int columns) {
        int[][] map = new int[rows][columns];
        int zero = rows * columns;
        int number = 1;
        int x = 0, y = 0;
        while (zero > 0 && isPossible(x, y, rows, columns, map, number)) {
            if (map[x][y] == 0) {
                zero--;
            }
            map[x][y] = number++;
            if ((number - 1)% 2 == 0) {
                x = x + 1 >= rows ? 0 : x + 1;
            } else {
                y = y + 1 >= columns ? 0 : y + 1;
            }
        }
        return map;
    }

    private boolean isPossible(int x, int y, int rows, int columns, int[][] map, int number) {
        if (rows == columns && x == rows - 1 && y == 0) {
            map[x][y] = number;
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] array = sol.solution(2, 5);
        print(array);
        System.out.println();
        array = sol.solution(3, 6); // 상태 체크 필요
        print(array);
    }

    private static void print(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
