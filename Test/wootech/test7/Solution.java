package Test.wootech.test7;

import java.util.Arrays;

public class Solution {

    public String[] solution(String[] grid, boolean clockwise) {
        char[][] map = new char[grid.length][grid[grid.length - 1].length()];
        char[][] tmp = new char[grid.length][grid[grid.length - 1].length()];
        for (int i = 0; i < grid.length; i++) {
            map[i] = grid[i].toCharArray();
        }
        if (clockwise) {
            for (int i = 0; i < grid[grid.length - 1].length(); i += 2) {
                int x = 0, y = 0;

            }
        } else {

        }
        String[] answer = new String[grid.length];
        int len = 1;
        for (int i = 0; i < grid.length; i++) {
            String str = "";
            for (int j = 0; j < len; j++) {
                str += tmp[i][j];
            }
            len += 2;
            answer[i] = str;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(new String[]{"1", "234", "56789"}, true))); // ["5","762","98431"]
        System.out.println(Arrays.toString(sol.solution(new String[]{"A","MAN","DRINK","WATER11"}, false)));   // ["1","K1R","NNIET","AAMRDAW"]
    }
}
