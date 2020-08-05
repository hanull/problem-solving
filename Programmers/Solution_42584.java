package Programmers;

public class Solution_42584 {
    public int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        for(int i=0; i<len-1; i++){
            for (int j=i+1; j<len; j++){
                if (prices[i] > prices[j]){
                    answer[i] = j-i;
                    break;
                }
                answer[i] = j-i;
            }
        }
        return answer;
    }
}
