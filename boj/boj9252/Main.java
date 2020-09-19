package boj.boj9252;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input1 = br.readLine();
        String input2 = br.readLine();
        int len1 = input1.length();
        int len2 = input2.length();
        int[][] dp = new int[len2 + 1][len1 + 1];

        for (int i = 1; i <= len2; i++) {
            for (int j = 1; j <= len1; j++) {
                if (input1.charAt(j - 1) == input2.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int r = len2;
        int c = len1;
        while (dp[r][c] != 0) {
            if (dp[r - 1][c] == dp[r][c]) {
                r--;
            } else if (dp[r][c - 1] == dp[r][c]) {
                c--;
            } else if (dp[r][c] - 1 == dp[r - 1][c - 1]) {
                sb.append(input1.charAt(c - 1));
                r--;
                c--;
            }
        }
        bw.write(dp[len2][len1] + "\n" + sb.reverse().toString());
        br.close();
        bw.close();
    }
}
