package Programmers.메뉴리뉴얼;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class Solution2 {

    static int max;

    public String[] solution(String[] orders, int[] course) {
        HashMap<String, Integer> courseMeals = new HashMap<>();
        for (int i = 0; i < course.length; i++) {
            max = 0;
            for (int j = 0; j < orders.length; j++) {
                if (course[i] > orders[j].length())
                    continue;
                char[] order = orders[j].toCharArray();
                Arrays.sort(order);
                pickMenu(courseMeals, 0, 0, course[i], new char[course[i]], order, orders[j].length());
            }
            pickBestMenu(courseMeals);
        }
        return addNewCourseMeals(courseMeals);
    }

    private void pickBestMenu(HashMap<String, Integer> courseMeals) {
        Queue<String> removeCourse = new ArrayDeque<>();
        for (String course : courseMeals.keySet()) {
            if (courseMeals.get(course) < max) {
                removeCourse.add(course);
            }
        }
        while (!removeCourse.isEmpty()) {
            courseMeals.remove(removeCourse.poll());
        }
    }

    private String[] addNewCourseMeals(HashMap<String, Integer> courseMeals) {
        List<String> answerList = new ArrayList<>();
        for (String course : courseMeals.keySet()) {
            if (courseMeals.get(course) >= 2) {
                answerList.add(course);
            }
        }
        Collections.sort(answerList);
        String[] answer = new String[answerList.size()];
        int index = 0;
        for (String course : answerList) {
            answer[index++] = course;
        }
        return answer;
    }

    private void pickMenu(HashMap<String, Integer> courseMeals, int count, int index, int targetCount, char[] menu,
        char[] order, int n) {
        if (count == targetCount) {
            String newMenu = new String(menu);
            int total = 1;
            if (courseMeals.containsKey(newMenu)) {
                total = courseMeals.get(newMenu) + 1;
                courseMeals.put(newMenu, total);
            } else {
                courseMeals.put(newMenu, 1);
            }
            max = Math.max(max, total);
            return;
        }
        for (int i = index; i < n; i++) {
            menu[count] = order[i];
            pickMenu(courseMeals, count + 1, i + 1, targetCount, menu, order, n);
        }
    }

    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        System.out.println(Arrays.toString(sol.solution(
            new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[] {2, 3, 4})));
        System.out.println(Arrays.toString(sol.solution(
            new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[] {2, 3, 5})));
        System.out.println(Arrays.toString(sol.solution(
            new String[] {"XYZ", "XWY", "WXA"}, new int[] {2, 3, 4})));
    }
}
