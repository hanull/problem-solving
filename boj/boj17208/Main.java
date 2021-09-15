package boj.boj17208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, maxBurger, maxFries;
    static int[][][] dp;
    static List<Order> list = new ArrayList<>();
    static class Order {
        int burger, fries;

        public Order(int burger, int fries) {
            this.burger = burger;
            this.fries = fries;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        maxBurger = stoi(st.nextToken());
        maxFries = stoi(st.nextToken());
        dp = new int[maxBurger + 1][maxFries + 1][N];
        initDP();
        int totalBurger = 0;
        int totalFries = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int burger = stoi(st.nextToken());
            int fries = stoi(st.nextToken());
            totalBurger += burger;
            totalFries += fries;
            if (burger > maxBurger || fries > maxFries) continue;
            list.add(new Order(burger, fries));
        }
        if (list.size() <= 1) {
            System.out.println(list.size());
            System.exit(0);
        }
        if (list.size() == N && totalBurger <= maxBurger && totalFries <= maxFries) {
            System.out.println(N);
            System.exit(0);
        }
        System.out.println(dfs(0, 0, 0, 0));
    }

    private static void initDP() {
        for (int i = 0; i <= maxBurger; i++) {
            for (int j = 0; j <= maxFries; j++) {
                for (int k = 0; k < N; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
    }

    private static int dfs(int burger, int fries, int index, int total) {
        if (index == list.size()) return 0;
        if (dp[burger][fries][index] >= 0) return dp[burger][fries][index];

        int answer = 0;
        // 주문을 선택할 경우
        if (burger + list.get(index).burger <= maxBurger && fries + list.get(index).fries <= maxFries) {
            answer = dfs(burger + list.get(index).burger, fries + list.get(index).fries, index + 1, total + 1) + 1;
        }
        // 주문을 선택하지 않을 경우
        answer = Math.max(answer, dfs(burger, fries, index + 1, total));
        return dp[burger][fries][index] = answer;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
