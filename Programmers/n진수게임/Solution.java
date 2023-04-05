package Programmers.n진수게임;

public class Solution {

    public String solution(int n, int t, int m, int p) {
        StringBuilder totalNums = new StringBuilder("0");
        for (int i = 1; i <= t * m; i++) {
            StringBuilder nums = new StringBuilder();
            int num = i;
            while (num > 0) {
                int temp = num % n;
                if (temp < 10) {
                    nums.append(temp);
                } else {
                    nums.append(calculateAlpha(temp));
                }
                num /= n;
            }
            totalNums.append(nums.reverse());
        }
        return pickNumbers(totalNums.toString(), t, p - 1, m);
    }

    private String calculateAlpha(final int num) {
        String[] alpha = {"A", "B", "C", "D", "E", "F"};
        return alpha[num % 10];
    }

    private String pickNumbers(final String nums, final int len, final int startIndex, final int memberCount) {
        StringBuilder answer = new StringBuilder();
        for (int i = startIndex; i < nums.length(); i += memberCount) {
            if (answer.length() == len) {
                break;
            }
            answer.append(nums.charAt(i));
        }
        return answer.toString();
    }
}
