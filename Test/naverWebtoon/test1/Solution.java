package Test.naverWebtoon.test1;

import java.util.Arrays;

public class Solution {

    public int solution(int[] prices, int[] discounts) {
        int answer = 0;
        Arrays.sort(prices);
        Arrays.sort(discounts);
        int len = prices.length -1;
        for (int i=discounts.length - 1; i>=0; i--) {
            prices[len] -= prices[len] * 0.01 * discounts[i];
            len--;
        }
        for (int i=0; i<prices.length; i++) {
            answer += prices[i];
        }
        return answer;
    }
}
