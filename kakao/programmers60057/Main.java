package kakao.programmers60057;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "xababcdcdababcdcd";
        System.out.println(sol.solution(s));
    }
}

class Solution {

    public int solution(String s) {
        int minLength = s.length();
        if (minLength <= 2) return minLength;   // input의 길이가 2이하 일 때, 압출 불가능

        for (int i = 1; i <= s.length()/2; i++) {
            int len = splitNum(s, i);
            if (minLength > len) minLength = len;
        }
        return minLength;
    }

    private int splitNum(String input, int splitSize) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        int len = input.length();

        while (i < len) {
            if (i + splitSize >= len) break;
            String tmp = input.substring(i, i + splitSize);
            j = i + splitSize;
            int cnt = 1;
            while (j + splitSize <= len && input.substring(j, j + splitSize).equals(tmp)) {
                j += splitSize;
                cnt++;
            }
            if (cnt > 1) {
                list.add(cnt + tmp);
            } else {
                list.add(tmp);
            }
            i = j;
        }
        while (i < len) {
            list.add(input.substring(i, i + 1));
            i++;
        }
        String res = "";
        for (String tmp : list) {
            res+=tmp;
        }
        return res.length();
    }
}
