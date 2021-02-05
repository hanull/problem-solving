package SWEA.계산기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

    static int N;
    static char[] infix;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= 10; tc++) {
            N = stoi(br.readLine());
            infix = br.readLine().toCharArray();
            System.out.println("#" + tc + " " + calculate());
        }
    }

    static int calculate() {
        String postfix = infixToPostfix();
        Stack<Integer> stack = new Stack<>();
        for (char ch : postfix.toCharArray()) {
            if (ch == '*') {
                int tmp = stack.pop() * stack.pop();
                stack.push(tmp);
            } else if (ch == '+') {
                int tmp = stack.pop() + stack.pop();
                stack.push(tmp);
            } else {
                stack.push(ch - '0');
            }
        }
        return stack.pop();
    }

    static String infixToPostfix() {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : infix) {
            // 숫자면 그냥 저장
            if (isDigit(ch)) {
                sb.append(ch);
            } else if (ch == '*') {
                if (!stack.isEmpty() && stack.peek() == '*') {
                    sb.append(ch);
                } else {        // 스택이 비었거나, + 연산자가 있을 경우
                    stack.push(ch);
                }
            } else {
                if (stack.isEmpty()) {  // + 연산자는 스택이 비었을 경우만 push 가능
                    stack.push(ch);
                } else {
                    sb.append(stack.pop());
                    if (!stack.isEmpty()) {
                        sb.append(ch);
                    } else {
                        stack.push(ch);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return String.valueOf(sb);
    }

    static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9' ? true : false;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
