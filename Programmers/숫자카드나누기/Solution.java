package Programmers.숫자카드나누기;

import java.util.Arrays;

public class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        int rightA = Math.max(arrayA[0], (int) Math.sqrt(arrayA[arrayA.length - 1]));
        int rightB = Math.max(arrayB[0], (int) Math.sqrt(arrayB[arrayB.length - 1]));

        // 1. A에서 모두 나누어지고, B에서 모두 나눌 수 없다.
        answer = Math.max(answer, findMaxNumberAbleToDivide(arrayA, arrayB, rightA));

        // 2. A에서 모두 나눌 수 없고, B에서 모두 나누어진다.
        answer = Math.max(answer, findMaxNumberAbleToDivide(arrayB, arrayA, rightB));
        return answer;
    }

    private int findMaxNumberAbleToDivide(final int[] possibleArray, final int[] imPossibleArray, int number) {
        while (number >= 2) {
            boolean flag = true;
            for (int i = 0; i < possibleArray.length; i++) {
                if (possibleArray[i] % number != 0 || imPossibleArray[i] % number == 0) {
                    flag = false;
                }
            }
            if (flag) {
                return number;
            }
            number--;
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{10, 17}, new int[]{5, 20}));
        System.out.println(sol.solution(new int[]{10, 20}, new int[]{5, 17}));
        System.out.println(sol.solution(new int[]{14, 35, 119}, new int[]{18, 30, 102}));
    }
}
