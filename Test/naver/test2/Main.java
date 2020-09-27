package Test.naver.test2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] blocks = {{0, 50},{0, 22},{2, 10},{1, 4},{4, -13}};
        int[] res = sol.solution(blocks);
        System.out.println(Arrays.toString(res));
    }
}

class Solution {
    public int[] solution(int[][] blocks) {
        int len = blocks.length;
        int[][] arr = new int[len][len];
        for (int i = 0; i < len; i++) {
            int j = blocks[i][0];
            arr[i][j] = blocks[i][1];
            if (i == 0) {
                continue;
            } else {
                search(i, j, arr);
            }
        }
        int[] res = getResult(len, arr);
        return res;
    }

    private int[] getResult(int len, int[][] arr) {
        int resLen = 0;
        for (int i = 0; i < len; i++) {
            resLen += i + 1;
        }
        int[] res = new int[resLen];
        int idx = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                res[idx++] = arr[i][j];
            }
        }
        return res;
    }

    private void search(int x, int y, int[][] arr) {
        int cur = y;
        while (cur - 1 >= 0) {
            int next = cur - 1;
            arr[x][next] = arr[x-1][cur-1] - arr[x][cur];
            cur = next;
        }
        cur = y;
        while (cur + 1 <= x) {
            int next = cur + 1;
            arr[x][next] = arr[x-1][cur] - arr[x][cur];
            cur = next;
        }
    }
}