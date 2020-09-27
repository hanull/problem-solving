package ebay.test2;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(1));
    }

}

class Solution {

    public int solution(int N) {
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }
}