package Test.naver.test1;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        String m = "acbbcdc";
        String k = "abc";
        System.out.println(sol.solution(m, k));
    }
}

class Solution {
    public String solution(String m, String k) {
        int lenM = m.length();
        int lenK = k.length();
        boolean[] check = new boolean[lenM];
        int i = 0;
        int j = 0;
        while (i < lenM && j < lenK) {
            if (m.charAt(i) == k.charAt(j)) {
                check[i] = true;
                i++;
                j++;
            } else {
                i++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int a = 0; a < lenM; a++) {
            if (!check[a]) {
                sb.append(m.charAt(a));
            }
        }
        return sb.toString();
    }
}