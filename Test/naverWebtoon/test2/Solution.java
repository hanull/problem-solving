package Test.naverWebtoon.test2;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public String[] solution(String s) {
        char[] array = s.toCharArray();
        List<String> leftString = new ArrayList<>();
        List<String> rightString = new ArrayList<>();
        int right = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            int move = 1;
            int end = right;
            while (array[i] != array[right]) {
                right--;
                move++;
            }
            if (i + move > right) {
                if (i != end + 1) leftString.add(s.substring(i, end + 1));
                break;
            }
            String newString = String.valueOf(array, right, move);
            String leftS = s.substring(i, i + move);
            if (leftS.equals(newString)) {
                leftString.add(leftS);
                rightString.add(leftS);
            }
            i += move - 1;
            right--;
        }
        String[] answer = new String[leftString.size() + rightString.size()];
        int index = 0;
        for (String str : leftString) {
            answer[index++] = str;
        }
        for (int i = rightString.size() - 1; i >= 0; i--) {
            answer[index++] = rightString.get(i);
        }
        return answer;
    }
}
