package Basic.knapsack;

import java.util.Scanner;

public class Knapsack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 물건의 개수
        int W = sc.nextInt();   // 가방의 무게

        int[] weight = new int[N + 1];  // 각 물건의 무게 정보
        int[] profit = new int[N + 1];  // 각 물건의 가치 정보
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            weight[i] = sc.nextInt();
            profit[i] = sc.nextInt();
        }

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (weight[i] <= w) {   // 가방에 i번째 물건을 넣을 수 있는 경우
                    dp[i][w] = Math.max(dp[i - 1][w - weight[i]] + weight[i], dp[i - 1][w]);
                } else {    // 가방에 i번째 물건을 넣을 수 없는 경우
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        System.out.println(dp[N][W]);
    }
}
