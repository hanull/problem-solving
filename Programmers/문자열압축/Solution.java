package Programmers.문자열압축;

public class Solution {

    public int solution(String s) {
        int len = s.length();
        int answer = len;

        // 문자열 길이가 2이하이면, 압축 불가
        if (len <= 2) return len;

        for (int i=1; i<=len/2; i++) {
            answer = Math.min(answer, calculate(s, i));
        }
        return answer;
    }

    private int calculate(String input, int size) {
        StringBuilder answer = new StringBuilder();

        String target = input.substring(0, size);
        int lastPoint = 0;
        int count = 0;
        for (int i = size; i <= input.length(); i += size) {
            String tmp = input.substring(i - size, i);
            if (target.equals(tmp)) {
                count++;
            } else {
                if (count > 1) answer.append(count);
                answer.append(target);
                target = tmp;
                count = 1;
            }
            if (i + size > input.length()) lastPoint = i;
        }
        if (count > 1) {
            answer.append(count).append(target).append(input.substring(lastPoint));
        } else {
            answer.append(input.substring(lastPoint - size));
        }
        return answer.length();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("aabbaccc"));
        System.out.println(sol.solution("ababcdcdababcdcd"));
        System.out.println(sol.solution("abcabcdede"));
        System.out.println(sol.solution("abcabcabcabcdededededede"));
        System.out.println(sol.solution("xababcdcdababcdcd"));
        System.out.println(sol.solution("abcabcabcdd"));
    }
}
