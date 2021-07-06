package Programmers.추석트래픽;

public class Solution {

    public int solution(String[] lines) {
        int answer = 0;
        int len = lines.length;
        int[] startTimes = new int[len];
        int[] endTimes = new int[len];

        changeTimes(lines, startTimes, endTimes);

        for (int i = 0; i < len; i++) {
            int totalA = 0;
            int totalB = 0;
            for (int j = 0; j < len; j++) {
                if (isRange(startTimes[i], startTimes[i] + 1000, startTimes[j], endTimes[j])) totalA++;
                if (isRange(endTimes[i], endTimes[i] + 1000, startTimes[j], endTimes[j])) totalB++;
            }
            answer = Math.max(answer, Math.max(totalA, totalB));
        }
        return answer;
    }

    private boolean isRange(int min, int max, int startTime, int endTime) {
        return (startTime >= min && startTime < max) ||     // 1. 시작점이 범위에 포함될 경우
                (endTime >= min && endTime < max) ||        // 2. 끝점이 범위에 포함될 경우
                (startTime <= min && endTime >= max);        // 3. 시작점과 끝점 모두 범위에 포함될 경우
    }

    private void changeTimes(String[] lines, int[] startTimes, int[] endTimes) {
        for (int i = 0; i < lines.length; i++) {
            String[] splitString = lines[i].split(" ");
            String[] time = splitString[1].split(":");
            int hour = Integer.parseInt(time[0]) * 3600 * 1000;
            int minute = Integer.parseInt(time[1]) * 60 * 1000;
            int second = (int) ((Double.parseDouble(time[2])) * 1000);
            int workTime = (int) (Double.parseDouble(splitString[2].replace("s", "")) * 1000);

            int endTime = hour + minute + second;
            int startTime = endTime - workTime + 1;
            startTimes[i] = startTime;
            endTimes[i] = endTime;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] lines = {
                "2016-09-15 01:00:04.002 2.0s",
                "2016-09-15 01:00:07.000 2s"
        };
        System.out.println(sol.solution(lines));
    }
}
