package boj.boj2729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            System.out.println(addBinary(a, b));
        }
    }

    private static String trim(String input) {
        if (input.charAt(0) == '1') return input;
        if (!input.contains("1")) return "0";
        int index = 0;
        while (index < input.length() && input.charAt(index) == '0') {
            index++;
        }
        return input.substring(index);
    }

    private static String addBinary(String a, String b) {
        StringBuilder answer = new StringBuilder();
        int left = a.length() - 1;
        int right = b.length() - 1;
        int carry = 0;
        while (left >= 0 || right >= 0) {
            int sum = carry;
            if (left >= 0) {
                sum += a.charAt(left) - '0';
                left--;
            }
            if (right >= 0) {
                sum += b.charAt(right) - '0';
                right--;
            }
            answer.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) {
            answer.append(carry);
        }
        return trim(answer.reverse().toString());
    }
}