package Test.codechallenge2.test1;

public class Solution {

    public int solution(int left, int right) {
        int answer = 0;
        for (int num = left; num <= right; num++) {
            if (checkDivisorCount(num)) answer += num;
            else answer -= num;
        }
        return answer;
    }

    private boolean checkDivisorCount(int num) {
        int count = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) count++;
        }
        return count % 2 == 0 ? true : false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int left = 24;
        int right = 27;
        System.out.println(sol.solution(left, right));
    }
}
