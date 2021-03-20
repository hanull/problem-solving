package Leetcode.firstUniqueCharacter;

public class Solution {

    static public int firstUniqChar(String s) {
        int[] alpa = new int['z' - 'a' + 1];
        for (char ch : s.toCharArray()) {
            alpa[ch - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (alpa[ch - 'a'] < 2) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("loveleetcode"));
    }
}
