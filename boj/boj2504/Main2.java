package boj.boj2504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        boolean flag = true;
        int total = 0;
        int mul = 1;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length; i++) {
            char ch = input[i];
            if (ch == '(' || ch == '[') {
                stack.push(ch);
                mul *= ch == '(' ? 2 : 3;
            } else {    // 닫힌 괄호 만날 때,j
                if (stack.isEmpty() || !isPair(stack.peek(), ch)) {
                    flag = false;
                    break;
                }
                if (input[i - 1] == '(' || input[i - 1] == '[') {
                    total += mul;
                }
                stack.pop();
                mul /= ch == ')' ? 2 : 3;
            }
        }
        if (!flag || !stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(total);
        }

    }

    static boolean isPair(char open, char close) {
        if (open == '(' && close == ')') {
            return true;
        }
        if (open == '[' && close == ']') {
            return true;
        }
        return false;
    }

}
