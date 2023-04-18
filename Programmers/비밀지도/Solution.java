package Programmers.비밀지도;

import java.util.*;

public class Solution {

    public String[] solution(int n, int[] arr1, int[] arr2) {
        boolean[][] map1 = new boolean[n][n];
        boolean[][] map2 = new boolean[n][n];
        checkMap(n, arr1, map1);
        checkMap(n, arr2, map2);
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (map1[i][j] || map2[i][j]) {
                    temp.append("#");
                } else {
                    temp.append(" ");
                }
            }
            answer[i] = temp.toString();
        }
        return answer;
    }

    private void checkMap(final int n, final int[] arr, final boolean[][] map) {
        for (int i = 0; i < n; i++) {
            char[] binary = Integer.toBinaryString(arr[i]).toCharArray();
            char[] temp = new char[n];
            int index = temp.length - binary.length;
            for (final char c : binary) {
                temp[index] = c;
                index++;
            }
            for (int j = 0; j < n; j++) {
                if (temp[j] == '1') {
                    map[i][j] = true;
                }
            }
        }
    }

    public String[] solution2(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String temp;
        for (int i = 0; i < n; i++) {
            String binary = Integer.toBinaryString(arr1[i] | arr2[i]);
            temp = String.format("%16s", binary);
            temp = temp.substring(16 - n);
            temp = temp.replaceAll("1", "#");
            temp = temp.replaceAll("0", " ");
            answer[i] = temp;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(
                Arrays.toString(sol.solution2(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28})));
    }
}
