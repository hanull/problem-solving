package Programmers.정수삼각형;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int len = triangle.length;
        int[][] dp = new int[len][len];
        dp[0][0] = triangle[0][0];

        for (int i=1; i<len; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            int size = triangle[i].length;
            for (int j=1; j<size - 1; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
            }
            dp[i][size - 1] = dp[i - 1][size - 2] + triangle[i][size - 1];
        }
        for (int i=0; i<len; i++) {
            answer = Math.max(answer, dp[len - 1][i]);
        }
        return answer;
    }
}
