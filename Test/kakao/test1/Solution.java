package Test.kakao.test1;

public class Solution {

    public int solution(int[] gift_cards, int[] wants) {
        int countCard[] = new int[100001];

        for (int i = 0; i < wants.length; i++) {
            countCard[wants[i]]++;
        }

        int answer = 0;
        for (int i = 0; i < gift_cards.length; i++) {
            int number = gift_cards[i];
            if (countCard[number] == 0) answer++;
            else countCard[number]--;
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] gift_cards = {4, 5, 3, 2, 1};
        int[] wants = {2, 4, 4, 5, 1};
        System.out.println(sol.solution(gift_cards, wants));
    }
}
