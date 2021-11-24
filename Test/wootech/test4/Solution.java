package Test.wootech.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public int[] solution(String s) {
        List<Integer> answerList = new ArrayList<>();
        char[] input = s.toCharArray();
        int left = 0;
        int right = input.length - 1;
        int count = 0;
        // 0번 인덱스를 기준으로, 맨 뒤부터 거꾸로 확인 (원형으로 연결)
        while (left < right && input[left] == input[right]) {
            count++;
            right--;
        }
        // 0번 인덱스를 기준으로, 앞으로 확인
        left = 0;
        while (left <= right && input[0] == input[left]) {
            count++;
            left++;
        }
        answerList.add(count);

        count = 1;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                if (input[i] == input[j]) {
                    count++;
                } else {
                    break;
                }
            }
            answerList.add(count);
            i += count - 1;
            count = 1;
        }
        int[] answer = new int[answerList.size()];
        int index = 0;
        for (Integer len : answerList) {
            answer[index++] = len;
        }
        Arrays.sort(answer);
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
//        System.out.println(Arrays.toString(sol.solution("aaabbaaa")));
//        System.out.println(Arrays.toString(sol.solution("wowwow")));
        System.out.println(Arrays.toString(sol.solution("aaaa")));
        System.out.println(Arrays.toString(sol.solution("abb")));
        System.out.println(Arrays.toString(sol.solution("abcd")));
        System.out.println(Arrays.toString(sol.solution("abaaccaaa")));
    }
}
