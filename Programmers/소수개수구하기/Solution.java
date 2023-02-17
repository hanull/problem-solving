package Programmers.소수개수구하기;

public class Solution {

    public int solution(int n, int k) {
        int answer = 0;
        String conversionNumber = conversion(n, k);
        String[] splitNumbers = conversionNumber.split("0");
        for (String number : splitNumbers) {
            if (!number.isEmpty() && isPrimeNumber(Long.parseLong(number))) {
                answer++;
            }
        }
        return answer;
    }

    private String conversion(final int n, final int k) {
        StringBuilder sb = new StringBuilder();
        int current = n;
        while (current > 0) {
            if (current % k < 10) {
                sb.append(current % k);
            } else {
                sb.append((char) current % k - 10 + 'A');
            }
            current /= k;
        }
        return sb.reverse().toString();
    }

    private boolean isPrimeNumber(final long number) {
        if (number == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(437674, 3));
        System.out.println(sol.solution(110011, 10));
    }
}
