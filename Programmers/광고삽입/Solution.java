package Programmers.광고삽입;

import java.util.*;

public class Solution {

    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = toInt(play_time);
        int advTime = toInt(adv_time);
        long[] timeCounts = new long[playTime + 1];
        for (String log : logs) {
            String[] temp = log.split("-");
            int start = toInt(temp[0]);
            int end = toInt(temp[1]);
            timeCounts[start]++;
            timeCounts[end]--;
        }

        // 누적합 1회 하면, 해당 시간에 시청한 사람의 수
        for (int i = 1; i <= playTime; i++) {
            timeCounts[i] += timeCounts[i - 1];
        }
        // 누적합 2회 하면, 해당 시간까지 동영상을 재생한 시간
        for (int i = 1; i <= playTime; i++) {
            timeCounts[i] += timeCounts[i - 1];
        }

        long maxTime = timeCounts[advTime - 1];
        int startTime = 0;
        for (int i = 0; i + advTime <= playTime; i++) {
            long temp = timeCounts[i + advTime] - timeCounts[i];
            if (maxTime < temp) {
                maxTime = temp;
                startTime = i + 1;
            }
        }
        return String.format("%02d:%02d:%02d", startTime / 3600, startTime / 60 % 60, startTime % 60);
    }

    private int toInt(final String time) {
        int[] temp = Arrays.stream(time.split(":"))
                .mapToInt(Integer::parseInt)
                .toArray();
        return temp[0] * 3600 + temp[1] * 60 + temp[2];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("50:00:00", "50:00:00",
                new String[]{"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}));
    }
}
