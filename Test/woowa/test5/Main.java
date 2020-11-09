package Test.woowa.test5;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        //System.out.println(sol.solution("1100", "0010", "1001", "1101100100101111001111000000"));
        System.out.println(sol.solution("10", "11", "00", "00011011"));
    }
}

class Solution {
    public String solution(String penter, String pexit, String pescape, String data) {
        StringBuilder sb = new StringBuilder();
        sb.append(penter);
        int splitSize = penter.length();
        for (int i = 0; i <= data.length() - splitSize; i += splitSize) {
            String splitData = data.substring(i, i + splitSize);
            if (isDuplicate(penter, pexit, pescape, splitData)) {
                sb.append(pescape);
            }
            sb.append(splitData);
        }
        sb.append(pexit);
        return sb.toString();
    }

    private boolean isDuplicate(String penter, String pexit, String pescape, String splitData) {
        if (splitData.equals(penter) || splitData.equals(pexit) || splitData.equals(pescape)) {
            return true;
        }
        return false;
    }
}