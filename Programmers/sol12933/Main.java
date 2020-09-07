package Programmers.sol12933;

import java.util.Arrays;
public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(118372));
    }
}

class Solution {
    public long solution(long n) {
        char[] chars = String.valueOf(n).toCharArray();
        Arrays.sort(chars);
        StringBuilder sb = new StringBuilder(new String(chars));
        return Long.parseLong(sb.reverse().toString());
    }
}

class Solution2 {
    public long solution(long n) {
        char[] chars = String.valueOf(n).toCharArray();
        Arrays.sort(chars);
        String str = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            str += chars[i];
        }
        return Long.parseLong(str);
    }
}