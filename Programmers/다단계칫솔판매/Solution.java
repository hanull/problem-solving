package Programmers.다단계칫솔판매;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    static HashMap<String, String> tree = new HashMap<>();
    static HashMap<String, Integer> profit = new HashMap<>();

    static public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            tree.put(enroll[i], referral[i]);
            profit.put(enroll[i], 0);
        }
        profit.put("-", 0);
        for (int i = 0; i < seller.length; i++) {
            String current = seller[i];
            int totalProfit = amount[i] * 100;

            while (!current.equals("-")) { // 부모가 있으면 10% 줌
                int fees = totalProfit / 10;
                int myProfit = totalProfit - fees;

                profit.put(current, profit.get(current) + myProfit);
                current = tree.get(current);
                totalProfit /= 10;
                if (totalProfit < 1) break;
            }
        }
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = profit.get(enroll[i]);
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};
        System.out.println(Arrays.toString(solution(enroll, referral, seller, amount)));
    }
}
