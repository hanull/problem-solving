package Programmers.비밀지도;

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
}
