package kakao.test2;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] order = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = {2, 3, 4};
        System.out.println(Arrays.toString(sol.solution(order,course)));
    }
}

class Solution {
    int[] maxCnt;

    public String[] solution(String[] orders, int[] course) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        ArrayList<String> list = new ArrayList<>();
        maxCnt = new int[course.length];

        for (int i = 0; i < orders.length; i++) {   // 코스 조합 구하기
            StringBuilder sb = new StringBuilder();
            boolean[] visited = new boolean[orders[i].length()];
            for (int j = 0; j < course.length; j++) {
                makeCourse(hm, visited, orders[i], 0, orders[i].length(), course[j], j);
            }
        }
        for (int i = 0; i < course.length; i++) {
            for (String tmp : hm.keySet()) {
                if (tmp.length() == course[i] && hm.get(tmp) == maxCnt[i] - 1) {
                    list.add(tmp);
                }
            }
        }

        // hashmap -> list
        // sort by count
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        Arrays.sort(result);
        return result;
    }

    private int getMaxCnt(HashMap<String, Integer> hm) {
        int res = 0;
        for (String tmp : hm.keySet()) {
            if (res < hm.get(tmp)) {
                res = hm.get(tmp);
            }
        }
        return res;
    }

    private void makeCourse(HashMap<String, Integer> hm, boolean[] visited, String order, int start, int n, int r, int j) {
        if (r == 0) {
            String newCourse = getString(visited, order, n);
            if (hm.containsKey(newCourse)) {
                hm.put(newCourse, hm.get(newCourse) + 1);
                if (maxCnt[j] < hm.get(newCourse) + 1) maxCnt[j] = hm.get(newCourse) + 1;
            } else {
                hm.put(newCourse, 1);
            }
            return;
        }
        for (int i = start; i < order.length(); i++) {
            visited[i] = true;
            makeCourse(hm, visited, order, i + 1, n, r - 1, j);
            visited[i] = false;
        }
    }

    private String getString(boolean[] visited, String order, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                sb.append(order.charAt(i));
            }
        }
        char[] tmp = new String(sb).toCharArray();
        Arrays.sort(tmp);
        return new String(tmp);
    }
}