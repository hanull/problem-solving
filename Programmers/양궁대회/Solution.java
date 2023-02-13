package Programmers.양궁대회;

import java.util.*;

public class Solution {

    static int[] answer;
    static int maxGap;

    public int[] solution(int n, int[] info) {
        answer = new int[info.length];
        dfs(0, 0, n, new int[info.length], info);
        return maxGap == 0 ? new int[]{-1} : answer;
    }

    private void dfs(final int index, final int count, final int n, final int[] ryan, final int[] info) {
        if (count == n) {
            // 점수 계산
            calculate(ryan, info);
            return;
        }
        for (int i = index; i <= 10; i++) {
            // 어퍼치의 개수보다 많은 경우, 더이상 i번 과녁에 쏠 필요가 없음
            if (ryan[i] > info[i]) {
                dfs(i + 1, count, n, ryan, info);
                continue;
            }
            ryan[i]++;
            dfs(i, count + 1, n, ryan, info);
            ryan[i]--;
        }
    }

    private static void calculate(final int[] ryan, final int[] info) {
        int ryanScore = 0;
        int apeachScore = 0;
        for (int i = 0; i <= 10; i++) {
            if (ryan[i] == 0 && info[i] == 0) {
                continue;
            }
            if (ryan[i] > info[i]) {
                ryanScore += 10 - i;
            } else {
                apeachScore += 10 - i;
            }
        }

        if (ryanScore <= apeachScore) {
            return;
        }
        int gap = ryanScore - apeachScore;
        if (gap < maxGap) {
            return;
        }
        // 점수 갱신
        // 1. 점수 차이가 더 큰 경우
        // 2. 점수 차이가 같고, 낮은 점수가 더 많은 경우
        if (gap > maxGap) {
            maxGap = gap;
            System.arraycopy(ryan, 0, answer, 0, 11);
        } else {
            for (int i = 10; i >= 0; i--) {
                if (ryan[i] < answer[i]) {
                    return;
                }
                if (ryan[i] > answer[i]) {
                    System.arraycopy(ryan, 0, answer, 0, 11);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
        System.out.println(Arrays.toString(sol.solution(1, new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0})));
        System.out.println(Arrays.toString(sol.solution(9, new int[]{0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1})));
        System.out.println(Arrays.toString(sol.solution(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3})));
    }
}
