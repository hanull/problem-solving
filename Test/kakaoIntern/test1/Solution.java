package Test.kakaoIntern.test1;

import java.util.HashMap;

public class Solution {

    static HashMap<String, Integer> map = new HashMap<>();
    static {
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
    }

    public int solution(String s) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                answer.append(ch);
            } else {
                for (int k = 3; k <= 5; k++) {
                    String temp = s.substring(i, i + k);
                    if (map.containsKey(temp)) {
                        answer.append(map.get(temp));
                        i += k - 1;
                        break;
                    }
                }
            }
        }
        return Integer.parseInt(new String(answer));
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("one4seveneight"));
        System.out.println(sol.solution("23four5six7"));
        System.out.println(sol.solution("2three45sixseven"));
        System.out.println(sol.solution("123"));
    }
}
