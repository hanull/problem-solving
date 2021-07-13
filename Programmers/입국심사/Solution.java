package Programmers.입국심사;

import java.util.Arrays;

public class Solution {

    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = 1;
        long right = (long) n * times[times.length - 1];
        while (left <= right) {
            long mid = (left + right) / 2;
            long total = 0;
            for (int time : times) {
                total += mid / time;
            }
            if (total < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 6;
        int[] times = {7, 10};
        System.out.println(sol.solution(n, times));
    }
}
