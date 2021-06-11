package Programmers.로또최고최저순위;

public class Solution {

    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        boolean[] numbers = new boolean[46];
        int correct = 0;
        int zero = 0;
        for (int i=0; i<lottos.length; i++) {
            if (lottos[i] == 0) zero++;
            else numbers[lottos[i]] = true;
        }
        for (int i=0; i<win_nums.length; i++) {
            if (numbers[win_nums[i]]) correct++;
        }
        answer[0] = getRank(correct + zero);
        answer[1] = getRank(correct);
        return answer;
    }

    public int getRank(int correct) {
        int rank = 6;
        if (correct == 6) rank = 1;
        else if (correct == 5) rank = 2;
        else if (correct == 4) rank = 3;
        else if (correct == 3) rank = 4;
        else if (correct == 2) rank = 5;
        return rank;
    }
}
