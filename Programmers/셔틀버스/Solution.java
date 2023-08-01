package Programmers.셔틀버스;

import java.util.*;

public class Solution {

    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> crewTimeTable = new PriorityQueue<>();
        for (String time : timetable) {
            String[] temp = time.split(":");
            crewTimeTable.add(Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]));
        }

        int targetTime = 540;
        int lastTime = 0;
        int answer = 0;
        PriorityQueue<Integer> passengers = new PriorityQueue<>();
        while (n > 0) {
            while (!crewTimeTable.isEmpty() && passengers.size() < m && crewTimeTable.peek() <= targetTime) {
                lastTime = crewTimeTable.peek();
                passengers.add(crewTimeTable.poll());
            }
            if (n == 1) {
                if (passengers.size() == 0) {
                    lastTime = targetTime;
                }
                if (passengers.size() == m) {
                    answer = lastTime - 1;
                } else {
                    answer = Math.max(targetTime, 540);
                }
            }
            targetTime += t;
            passengers.clear();
            n--;
        }
        return toStringPattern(answer);
    }

    private String toStringPattern(int time) {
        String answer = "";
        if (time / 60 < 10) {
            answer += "0" + time / 60;
        } else {
            answer += time / 60;
        }
        answer += ":";
        if (time % 60 < 10) {
            answer += "0" + time % 60;
        } else {
            answer += time % 60;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(sol.solution(2, 5, 5, new String[]{"09:00", "09:00", "09:00", "09:01"}));
        System.out.println(sol.solution(10, 60, 45,
                new String[]{"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59",
                        "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }
}
