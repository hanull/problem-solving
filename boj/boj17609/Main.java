package boj.boj17609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        StringBuilder result = new StringBuilder();
        while (T-- > 0) {
            String input = br.readLine();
            if (isPalindrome(input)) {
                result.append(0);
            } else if (isPseudoPalindrome(input)) {
                result.append(1);
            } else {
                result.append(2);
            }
            result.append("\n");
        }
        System.out.print(result);

    }

    static boolean isPseudoPalindrome(String input) {
        int left = 0;
        int right = input.length() - 1;
        boolean flag = true;
        while (flag && left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                if (isPalindrome(input.substring(left + 1, right + 1))) break;
                else if (isPalindrome(input.substring(left, right))) break;
                else flag = false;
            }
            left++;
            right--;
        }
        return flag;
    }

    static boolean isPalindrome(String input) {
        int left = 0;
        int right = input.length() - 1;
        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
