package Programmers.이모티콘할인행사;

import java.util.*;

public class Solution {

    static int[] answer = new int[2];
    static int[] discountRates = {40, 30, 20, 10};

    public int[] solution(int[][] users, int[] emoticons) {
        dfs(0, new int[emoticons.length], users, emoticons);
        return answer;
    }

    private void dfs(final int count, final int[] selectedDiscount, final int[][] users, final int[] emoticons) {
        // 할인율 선택 완료
        // 플러스 가입 수, 총 매출액 계산
        if (count == emoticons.length) {
            int[] newEmoticonPrices = calculateDiscountedPrice(emoticons, selectedDiscount);

            int plusService = 0;
            int salesRevenue = 0;
            for (final int[] user : users) {
                int targetDiscountRate = user[0];
                int totalPurchasePrice = 0;
                for (int j = 0; j < emoticons.length; j++) {
                    // 기준 할인율을 넘으면, 이모티콘 구매
                    if (discountRates[selectedDiscount[j]] >= targetDiscountRate) {
                        totalPurchasePrice += newEmoticonPrices[j];
                    }
                }
                // 구입한 비용이 기준을 넘으면,플러스 서비스에 가입
                if (totalPurchasePrice >= user[1]) {
                    plusService++;
                } else {
                    salesRevenue += totalPurchasePrice;
                }
            }

            if (plusService > answer[0]) {
                answer[0] = plusService;
                answer[1] = salesRevenue;
            } else if (plusService == answer[0] && salesRevenue > answer[1]) {
                answer[1] = salesRevenue;
            }
            return;
        }
        // 할인율 선택
        for (int i = 0; i < 4; i++) {
            selectedDiscount[count] = i;
            dfs(count + 1, selectedDiscount, users, emoticons);
        }
    }

    private int[] calculateDiscountedPrice(final int[] emoticons, final int[] selectedDiscount) {
        int[] newEmotionPrices = new int[emoticons.length];
        for (int i = 0; i < emoticons.length; i++) {
            newEmotionPrices[i] = emoticons[i] * (100 - discountRates[selectedDiscount[i]]) / 100;
        }
        return newEmotionPrices;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000})));
        System.out.println(Arrays.toString(
                sol.solution(
                        new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}},
                        new int[]{1300, 1500, 1600, 4900})));
    }
}
