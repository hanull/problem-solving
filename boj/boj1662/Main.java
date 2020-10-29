package boj.boj1662;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static char[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        System.out.println(solve());
    }

    private static int solve() {
        int len = input.length - 1;
        Stack<Integer> stack = new Stack<>();
        while (len >= 0) {
            char ch = input[len];
            if (ch == ')') {
                stack.push(-1);
            } else if (ch == '(') {
                int total = 0;
                while (stack.peek() != -1) {
                    total += stack.pop();
                }
                stack.pop();
                int cnt = input[--len] - '0';
                int num = cnt * total;
                stack.push(num);
            } else {
                stack.push(1);
            }
            len--;
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

}
