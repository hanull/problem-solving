package boj.boj9252;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input1 = br.readLine();
        String input2 = br.readLine();
        int len1 = input1.length();
        int len2 = input2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            char c1 = input1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char c2 = input2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Integer.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = len1;
        int j = len2;
        int max = dp[i][j];
        while (dp[i][j] != 0) {
            if (dp[i - 1][j] == max) {
                i--;
            } else if (dp[i][j - 1] == max) {
                j--;
            } else {
                sb.append(input1.charAt(i - 1));
                max--;
                i--;
                j--;
            }
        }
        System.out.println(dp[len1][len2]);
        System.out.println(sb.reverse().toString());
    }
}
