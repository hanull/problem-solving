package Programmers.개인정보수집유효기간;

import java.util.*;

public class Solution {

    public int[] solution(String today, String[] terms, String[] privacies) {
        int todayDate = calculateDate(today);
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            final String[] temp = term.split(" ");
            termMap.put(temp[0], Integer.parseInt(temp[1]) * 28);
        }
        List<Integer> answer = new ArrayList<>();
        int index = 1;
        for (String privacy : privacies) {
            String[] temp = privacy.split(" ");
            int privacyDate = calculateDate(temp[0]) + termMap.get(temp[1]) - 1;
            if (privacyDate < todayDate) {
                answer.add(index);
            }
            index++;
        }
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private int calculateDate(final String date) {
        String[] temp = date.split("\\.");
        return Integer.parseInt(temp[0]) * 12 * 28 +
                Integer.parseInt(temp[1]) * 28 +
                Integer.parseInt(temp[2]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution("2022.05.19",
                new String[]{"A 6", "B 12", "C 3"},
                new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"})));
        System.out.println(Arrays.toString(sol.solution("2020.01.01",
                new String[]{"Z 3", "D 5"},
                new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"})));
    }
}
