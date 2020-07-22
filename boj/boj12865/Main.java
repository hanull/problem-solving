package boj.boj12865;

import java.util.Scanner;

public class Main {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    int[] w = new int[n+1];
    int[] v = new int[n+1];
    int[][] dp = new int[n+1][k+1];
    for(int i=1; i<=n; i++) {
      w[i]= sc.nextInt();
      v[i] = sc.nextInt();
    }
    for(int i=1; i<=n; i++) {
      for(int j=1; j<=k; j++) {
        if (w[i] <= j) {
          int tmp1 = v[i] + dp[i-1][j-w[i]];
          int tmp2 = dp[i-1][j];
          dp[i][j] = Math.max(tmp1,tmp2);
        }
        else
          dp[i][j] = dp[i-1][j];
      }
    }
    System.out.println(dp[n][k]);
  }
}
