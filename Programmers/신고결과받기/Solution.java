package Programmers.신고결과받기;

import java.util.*;

public class Solution {

    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> logById = new HashMap<>();
        Map<String, Set<String>> countByTarget = new HashMap<>();
        for (String id : id_list) {
            logById.put(id, new HashSet<>());
            countByTarget.put(id, new HashSet<>());
        }
        for (String re : report) {
            String[] splitName = re.split(" ");
            String reporter = splitName[0];
            String target = splitName[1];
            logById.get(reporter).add(target);
            countByTarget.get(target).add(reporter);
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            int count = 0;
            for (String target : logById.get(id_list[i])) {
                if (countByTarget.get(target).size() >= k) {
                    count++;
                }
            }
            answer[i] = count;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(new String[]{"muzi", "frodo", "apeach", "neo"},
                new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"},
                2)));
    }
}
