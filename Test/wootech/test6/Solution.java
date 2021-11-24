package Test.wootech.test6;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    static double MONDAY_START = 13;
    static double FRIDAY_END = 18;

    public String solution(double time, String[][] plans) {
        List<String> answerList = new ArrayList<>();
        for (String[] plan : plans) {
            String destinations = plan[0];
            double departureTime = getTime(plan[1]);
            double arrivalTime = getTime(plan[2]);
            double needTime = getNeedTime(departureTime, arrivalTime);

            // 남은 휴가 시간으로 갈 수 없는 경우, 종료
            if (needTime > time) {
                break;
            } else {    // 갈 수 있는 경우, 여행지 추가
                time = time - needTime;
                answerList.add(destinations);
            }
        }
        return answerList.size() == 0 ? "호치민" : answerList.get(answerList.size() - 1);
    }

    private double getNeedTime(double departureTime, double arrivalTime) {
        double needTime = 0;
        // 금요일 퇴근 시간보다 빠르게 출발 할 경우, 휴가 시간 필요
        if (FRIDAY_END > departureTime) {
            needTime += FRIDAY_END - departureTime;
        }
        // 월요일 출근 시간보다 늦게 도착하는 경우, 휴가 시간 필요
        if (MONDAY_START < arrivalTime) {
            needTime += arrivalTime - MONDAY_START;
        }
        return needTime;
    }

    private double getTime(String input) {
        double time;
        if (input.contains("PM")) {
            String[] tmp = input.split("PM");
            time = Double.parseDouble(tmp[0]) + 12;
        } else {
            String[] tmp = input.split("AM");
            time = Double.parseDouble(tmp[0]);
        }
        return time;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[][] plans = {{"홍콩", "11PM", "1PM"}, {"엘에이", "3PM", "2PM"}};
        System.out.println(sol.solution(4, plans));
    }
}
