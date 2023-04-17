package Programmers.다트게임;

public class Solution {

    public int solution(String dartResult) {
        int[] numbers = {0, 0, 0};
        int index = 0;
        for (int i = 0; i < dartResult.length(); i++) {
            char ch = dartResult.charAt(i);
            if (Character.isDigit(ch)) {
                if (ch == '1' && dartResult.charAt(i + 1) == '0') {
                    numbers[index] = 10;
                    i++;
                } else {
                    numbers[index] = ch - '0';
                }
                continue;
            }
            if (ch == 'D') {
                numbers[index] = (int) Math.pow(numbers[index], 2);
            }
            if (ch == 'T') {
                numbers[index] = (int) Math.pow(numbers[index], 3);
            }
            if (i + 1 >= dartResult.length() || Character.isDigit(dartResult.charAt(i + 1))) {
                index++;
                continue;
            }
            ch = dartResult.charAt(i + 1);
            if (ch == '*') {
                if (index > 0) {
                    numbers[index - 1] *= 2;
                }
                numbers[index] *= 2;
            }
            if (ch == '#') {
                numbers[index] *= -1;
            }
            index++;
            i++;
        }
        return numbers[0] + numbers[1] + numbers[2];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("1S2D*3T"));
        System.out.println(sol.solution("1D2S#10S"));
        System.out.println(sol.solution("1D2S0T"));
        System.out.println(sol.solution("1S*2T*3S"));
        System.out.println(sol.solution("1D#2S*3S"));
        System.out.println(sol.solution("1T2D3D#"));
        System.out.println(sol.solution("1D2S3T*"));
    }
}
