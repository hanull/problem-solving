package boj.boj1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        char[] input = br.readLine().toCharArray();

        for (char ch : input) {
            if (ch >= 'A' && ch <= 'Z') {
                answer.append(ch);
            } else {
                if (ch == '(') {
                    stack.add(ch);
                    continue;
                } else if (ch == ')') {
                    while (!stack.isEmpty()) {
                        char tmp = stack.pop();
                        if (tmp == '(') break;
                        answer.append(tmp);
                    }
                    continue;
                } else if (!stack.isEmpty() && getPoint(stack.peek()) >= getPoint(ch)) {
                    while (!stack.isEmpty() && getPoint(stack.peek()) >= getPoint(ch)) {
                        answer.append(stack.pop());
                    }
                }
                stack.add(ch);
            }
        }
        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }
        System.out.println(answer);
    }

    private static int getPoint(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        }
        return 0;
    }
}
