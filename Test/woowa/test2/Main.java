package Test.woowa.test2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution("1234", "-")));
    }
}

class Solution {
    public long[] solution(String s, String op) {
        int len = s.length();
        long[] answer = new long[len - 1];
        for (int i = 0; i < len - 1; i++) {
            long left = Long.valueOf(s.substring(0, i + 1));
            long right = Long.valueOf(s.substring(i + 1, len));
            long value = cal(left, right, op);
            answer[i] = value;
        }
        return answer;
    }

    private long cal(long left, long right, String op) {
        long total = 0;
        switch (op) {
            case "+":
                total = left + right;
                break;
            case "-":
                total = left - right;
                break;
            case "*":
                total = left * right;
                break;
        }
        return total;
    }
}