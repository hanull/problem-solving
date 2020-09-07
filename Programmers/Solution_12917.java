package Programmers;

import java.util.Arrays;

public class Solution_12917 {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("arZbcdeaaafggggg"));
    }
}

class Solution {
    public String solution(String s) {
        char[] input = s.toCharArray();
        Arrays.sort(input);
        StringBuilder sb = new StringBuilder(String.valueOf(input));
        return sb.reverse().toString();
    }
}
