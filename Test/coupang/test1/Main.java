package Test.coupang.test1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(14)));
    }
}

class Solution{
    public int[] solution(int N){
        int[] answer = new int[2];
        for (int i=2; i<10; i++) {
            int changedNum = getChange(N, i);
            int mul = getMul(changedNum);
            if (mul >= answer[1]) {
                answer[0] = i;
                answer[1] = mul;
            }
        }
        return answer;
    }

    private int getMul(int input) {
        int res = 1;
        while (input > 0) {
            int tmp = input % 10;
            if (tmp != 0) res *= tmp;
            input /= 10;
        }
        return res;
    }

    private int getChange(int n, int d) {
        ArrayList<Character> list = new ArrayList<>();
        while (n > 0) {
            if (n % d < 10) {
                list.add((char)(n % d + '0'));
            } else {
                list.add((char)(n % d - 10 + 'A'));
            }
            n /= d;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i>=0; i--) {
            sb.append(list.get(i));
        }
        return Integer.valueOf(sb.toString());
    }
}