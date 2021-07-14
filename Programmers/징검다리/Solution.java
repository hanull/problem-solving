package Programmers.징검다리;

import java.util.Arrays;

public class Solution {

    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        int left = 0;
        int right = distance;
        while (left <= right) {
            int mid = (left + right) / 2;
            int current = 0;
            int remove = 0;
            for (int i = 0; i < rocks.length; i++) {
                int gap = rocks[i] - current;
                if (gap < mid) {
                    remove++;
                } else {
                    current = rocks[i];
                }
            }
            if (remove > n) {
                right = mid - 1;
            } else {
                answer = Math.max(answer, mid);
                left = mid + 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        System.out.println(sol.solution(distance, rocks, n));
    }
}
