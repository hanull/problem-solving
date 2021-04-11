package line.test2020.test3;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(73425));

    }
}

class Solution {
    public int[] solution(int n) {
        if (n < 10) {
            return new int[]{0, n};
        }
        int[] res = new int[2];
        int count = 0;
        int total = 0;
        String input = String.valueOf(n);
        int len = input.length();

        while (len > 1) {

        }
        res[0] = count;
        res[1] = total;
        return res;
    }
}
