package Test.naverWebtoon.test3;

public class Solution {

    public int solution(String s, String t) {
        StringBuilder sb = new StringBuilder(s);
        int count = 0;
        while (sb.indexOf(t) >= 0) {
            count++;
            int idx = sb.indexOf(t);
            sb.delete(idx, idx+t.length());
        }
        return count;
    }
}
