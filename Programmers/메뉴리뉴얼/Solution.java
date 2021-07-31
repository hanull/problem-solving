package Programmers.메뉴리뉴얼;

import java.util.*;

public class Solution {

    static public String[] solution(String[] orders, int[] course) {
        List<String>[] answerList = new ArrayList[course.length];
        List<String>[] list = new ArrayList[course.length];
        for (int i=0; i<course.length; i++) {
            answerList[i] = new ArrayList<>();
            list[i] = new ArrayList<>();
        }
        for (int i=0; i<course.length; i++) {
            for (int j=0; j<orders.length; j++) {
                if (course[i] == orders[j].length()) list[i].add(orders[j]);
            }
        }
        int totalCount = 0;
        for (int tc=0; tc<course.length; tc++) {
            int max = 0;
            for (String target : list[tc]) {   // x개의 메뉴로 이루어진 코스를 포함하고 있는 수 체크
                int count = 0;
                for (String str : orders) {
                    if (str.contains(target)) count++;
                }
                if (count > max) {
                    max = count;
                    answerList[tc].clear();
                    answerList[tc].add(target);
                } else if (count == max) {
                    answerList[tc].add(target);
                }
            }
            if (max < 2) answerList[tc].clear();
            totalCount += answerList[tc].size();
        }
        String[] answer = new String[totalCount];
        int idx = 0;
        for (int i=0; i<course.length; i++) {
            for (String str : answerList[i]) {
                answer[idx++] = str;
            }
        }
        Arrays.sort(answer);
        return answer;
    }

    public static void main(String[] args) {
        String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = {2, 3, 4};
        System.out.println(Arrays.toString(solution(orders, course)));
    }
}