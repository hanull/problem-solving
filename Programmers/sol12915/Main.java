package Programmers.sol12915;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] strings = {"sun", "bed", "car"};
        System.out.println(sol.solution(strings, 1));
    }
}

class Solution {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.charAt(n) == o2.charAt(n)) {
                    return o1.compareTo(o2);
                }
                return Integer.compare(o1.charAt(n), o2.charAt(n));
            }
        });
        return strings;
    }
}
