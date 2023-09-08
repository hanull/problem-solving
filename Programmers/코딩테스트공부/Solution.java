package Programmers.코딩테스트공부;

public class Solution {

    public int solution(int alp, int cop, int[][] problems) {
        int maxAlgo = 0;
        int maxCoding = 0;
        for (int[] problem : problems) {
            maxAlgo = Math.max(maxAlgo, problem[0]);
            maxCoding = Math.max(maxCoding, problem[1]);
        }

        int[][] dp = new int[maxAlgo + 2][maxCoding + 2];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        alp = Math.min(alp, maxAlgo);
        cop = Math.min(cop, maxCoding);
        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlgo; i++) {
            for (int j = cop; j <= maxCoding; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                for (int[] problem : problems) {
                    int algoRequest = problem[0];
                    int codingRequest = problem[1];
                    int algoReward = problem[2];
                    int codingReward = problem[3];
                    int cost = problem[4];

                    if (i >= algoRequest && j >= codingRequest) {
                        int nextAlgo = Math.min(i + algoReward, maxAlgo);
                        int nextCoding = Math.min(j + codingReward, maxCoding);
                        dp[nextAlgo][nextCoding] = Math.min(dp[nextAlgo][nextCoding], dp[i][j] + cost);
                    }
                }
            }
        }

        return dp[maxAlgo][maxCoding];
    }
}
