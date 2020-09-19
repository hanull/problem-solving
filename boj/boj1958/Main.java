package boj.boj1958;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input1 = br.readLine();
        String input2 = br.readLine();
        String input3 = br.readLine();
        int len1 = input1.length();
        int len2 = input2.length();
        int len3 = input3.length();
        int[][][] dp = new int[len1 + 1][len2 + 1][len3 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                for (int k = 1; k <= len3; k++) {
                    if (input1.charAt(i - 1) == input2.charAt(j - 1) && input1.charAt(i - 1) == input3.charAt(k - 1)) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                    }
                }
            }
        }

        int x = len1;
        int y = len2;
        int z = len3;
        StringBuilder sb = new StringBuilder();
        while (dp[x][y][z] != 0) {
            if (dp[x - 1][y][z] == dp[x][y][z]) {
                x--;
            } else if (dp[x][y - 1][z] == dp[x][y][z]) {
                y--;
            } else if (dp[x][y][z - 1] == dp[x][y][z]) {
                z--;
            } else if (dp[x][y][z] - 1 == dp[x - 1][y - 1][z - 1]) {
                sb.append(input1.charAt(x - 1));
                x--; y--; z--;
            }
        }
        System.out.println(dp[len1][len2][len3]);
        System.out.println(sb.reverse().toString());
        br.close();
    }
}
