package Programmers.보석쇼핑;

import java.util.*;

public class Solution {

    /**
     * 효율성 fail
     * 모든 경우를 다 해보는 방법으로 했기 때문에 시간초과 발생
     */
    public int[] solution1(String[] gems) {
        Set<String> allTypesOfGem = new HashSet<>(Arrays.asList(gems));
        Set<String> purchases = new HashSet<>();
        int[] answer = {1, gems.length};
        for (int i = 0; i <= gems.length - allTypesOfGem.size(); i++) {
            for (int j = i; j < gems.length; j++) {
                purchases.add(gems[j]);
                if (purchases.size() == allTypesOfGem.size()) {
                    if (answer[1] - answer[0] > j - i) {
                        answer[0] = i + 1;
                        answer[1] = j + 1;
                    }
                    break;
                }
            }
            purchases.clear();
        }
        return answer;
    }

    public int[] solution(String[] gems) {
        Set<String> allTypesOfGem = new HashSet<>(Arrays.asList(gems));
        int[] answer = {1, gems.length};

        Map<String, Integer> purchases = new HashMap<>();
        int left = 0;
        for (int right = 0; right < gems.length; right++) {
            if (purchases.containsKey(gems[right])) {
                purchases.put(gems[right], purchases.get(gems[right]) + 1);
            } else {
                purchases.put(gems[right], 1);
            }

            while (left <= right && purchases.get(gems[left]) > 1) {
                purchases.put(gems[left], purchases.get(gems[left]) - 1);
                left++;
            }

            // 모든 종류의 보석을 1개 이상씩 담은 경우
            if (purchases.size() == allTypesOfGem.size()) {
                if (answer[1] - answer[0] > right - left) {
                    answer[1] = right + 1;
                    answer[0] = left + 1;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(
                sol.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
    }
}
