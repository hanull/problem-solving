package Programmers.압축;

import java.util.*;

public class Solution {

    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            map.put(String.valueOf((char) (i - 1 + 'A')), i);
        }
        List<Integer> answerNumbers = new ArrayList<>();
        for (int i = 0; i < msg.length(); i++) {
            boolean flag = false;
            for (int j = i; j < msg.length(); j++) {
                String temp = msg.substring(i, j + 1);
                if (!map.containsKey(temp)) {
                    answerNumbers.add(map.get(msg.substring(i, j)));
                    map.put(temp, map.size() + 1 );
                    i = j - 1;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                answerNumbers.add(map.get(msg.substring(i)));
                map.put(msg.substring(i), map.size() + 1);
                i = msg.length() - 1;
            }
        }
        int[] answer = new int[answerNumbers.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerNumbers.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution("KAKAO")));
        System.out.println(Arrays.toString(sol.solution("TOBEORNOTTOBEORTOBEORNOT")));
        System.out.println(Arrays.toString(sol.solution("ABABABABABABABAB")));
    }
}
