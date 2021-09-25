package Programmers.영어끝말잇기;

import java.util.Arrays;
import java.util.HashSet;

public class Solution {

    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        HashSet<String> used = new HashSet<>();
        int match = 1;
        int index = 1;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (index == n + 1) {
                match++;
                index = 1;
            }
            if (i != 0 && used.contains(word) || i != 0 && !isPossible(words[i - 1].charAt(words[i - 1].length() - 1), words[i].charAt(0))) {
                answer[0] = index;
                answer[1] = match;
                break;
            }
            used.add(word);
            index++;
        }
        return answer;
    }

    private boolean isPossible(char left, char right) {
        return left == right;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
//        System.out.println(Arrays.toString(sol.solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));
//        System.out.println(Arrays.toString(sol.solution(5, new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"})));
        System.out.println(Arrays.toString(sol.solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"})));

    }
}
