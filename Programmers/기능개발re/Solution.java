package Programmers.기능개발re;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answerList = new ArrayList<>();
        int count = 1;
        int currentTime = getProgress(progresses[0], speeds[0]);
        int len = progresses.length;
        for (int i=1; i<len; i++) {
            int progress = getProgress(progresses[i], speeds[i]);
            if (currentTime < progress) {
                answerList.add(count);
                count = 1;
                currentTime = progress;
            } else {
                count++;
            }
            if (i == len - 1) {
                answerList.add(count);
            }
        }
        int[] answer = listToArray(answerList);
        return answer;
    }

    private int[] listToArray(List<Integer> answerList) {
        int[] answer = new int[answerList.size()];
        for (int i=0; i<answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    private int getProgress(int progress, int speed) {
        int remain = 100 - progress;
        return remain % speed == 0 ? remain / speed : remain / speed + 1;
    }
}
