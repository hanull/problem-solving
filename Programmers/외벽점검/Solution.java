package Programmers.외벽점검;

import java.util.*;

public class Solution {

    static int answer = Integer.MAX_VALUE;

    public int solution(int n, int[] weak, int[] dist) {
        boolean[] broken = new boolean[n];
        for (int index : weak) {
            broken[index] = true;
        }
        dfs(0, dist.length - 1, weak, dist, broken, new boolean[weak.length]);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private void dfs(int fixCount, final int distIndex, final int[] weak, final int[] dist, final boolean[] broken,
                     final boolean[] fixPoints) {
        int count = dist.length - 1 - distIndex;
        // 이미 갱신한 최소값보다 큰 경우
        if (count >= answer) {
            return;
        }

        // 취약 부분 모두 고쳤는지 확인
        if (fixCount == weak.length && isAllDone(broken)) {
            answer = count;
            return;
        }

        // 고칠 수 없는 경우
        if (distIndex == -1) {
            return;
        }

        // 고칠 부분
        int fixLen = dist[distIndex];
        for (int i = 0; i < weak.length; i++) {
            if (fixPoints[i]) {
                continue;
            }
            fixPoints[i] = true;
            List<Integer> fixed = fix(weak[i], fixLen, broken);
            dfs(fixCount + fixed.size(), distIndex - 1, weak, dist, broken, fixPoints);
            for (int index : fixed) {
                broken[index] = true;
            }
            fixPoints[i] = false;
        }
    }

    private boolean isAllDone(final boolean[] broken) {
        for (boolean flag : broken) {
            if (flag) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> fix(int index, int fixLen, final boolean[] broken) {
        List<Integer> fixed = new ArrayList<>();
        while (fixLen >= 0) {
            if (index == broken.length) {
                index = 0;
            }
            if (broken[index]) {
                fixed.add(index);
                broken[index] = false;
            }
            index++;
            fixLen--;
        }
        return fixed;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
//        System.out.println(sol.solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));
    }
}
