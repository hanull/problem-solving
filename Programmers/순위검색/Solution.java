package Programmers.순위검색;

import java.util.*;

public class Solution {

    static Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        for (String s : info) {
            String[] split = s.split(" ");
            findKeys(0, new String[split.length], split);
        }
        int[] answer = new int[query.length];
        int index = 0;
        for (String s : query) {
            String[] split = s.split(" ");
            String key = split[0] + split[2] + split[4] + split[6];
            if (map.containsKey(key)) {
                answer[index] = getCountOfBiggerNumber(map.get(key), Integer.parseInt(split[7]));
            }
            index++;
        }
        return answer;
    }

    private void findKeys(final int index, final String[] temp, final String[] info) {
        if (index == 4) {
            String key = temp[0] + temp[1] + temp[2] + temp[3];
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(Integer.parseInt(info[4]));
            return;
        }
        temp[index] = info[index];
        findKeys(index + 1, temp, info);
        temp[index] = "-";
        findKeys(index + 1, temp, info);
    }

    private int getCountOfBiggerNumber(List<Integer> numbers, int min) {
        Collections.sort(numbers);
        int left = 0;
        int right = numbers.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (numbers.get(mid) < min) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return numbers.size() - left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
//        System.out.println(Arrays.toString(sol.solution(
//                new String[]{"java backend junior pizza 150", "python frontend senior chicken 210",
//                        "python frontend senior chicken 150", "cpp backend senior pizza 260",
//                        "java backend junior chicken 80", "python backend senior chicken 50"},
//                new String[]{"java and backend and junior and pizza 100",
//                        "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
//                        "- and backend and senior and - 150", "- and - and - and chicken 100",
//                        "- and - and - and - 150"})));
        System.out.println(Arrays.toString(sol.solution(
                new String[]{"java backend junior pizza 150", "cpp backend junior pizza 210",
                        "python backend junior pizza 155"},
                new String[]{"- and backend and junior and pizza 150"})));
    }
}
