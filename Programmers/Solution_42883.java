package Programmers;

public class Solution_42883 {
    public String solution(String number, int k) {
        int len = number.length();
        char[] res = new char[len-k];
        int find = len-k;
        int start = 0;
        while (find > 0) {
            int tmp = getMaxPoint(number.substring(start, len-find+1));
            res[res.length-find]=number.charAt(start+tmp);
            find--;
            start = start+tmp+1;
        }
        return new String(res);
    }
    public int getMaxPoint(String num) {
        int max = Integer.MIN_VALUE;
        int res = 0;
        for(int i=0; i<num.length(); i++) {
            int tmp = num.charAt(i)-'0';
            if (max < tmp) {
                max = tmp;
                res = i;
            }
        }
        return res;
    }
}
