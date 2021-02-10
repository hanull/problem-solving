package boj.boj1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Main2 {

    static char[] infixStr;
    static HashMap<Character, Integer> priority = new HashMap<>();
    static {
        priority.put('(', 0);
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        infixStr = br.readLine().toCharArray();
        System.out.println(infixToPrefix());
    }

    static String infixToPrefix() {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char ch : infixStr) {
            if (ch == '(' ) {    // '(' 괄호 경우, 스택에 push
                stack.push(ch);
            } else if (ch == ')') { // ')' 괄호일 경우, 열린 괄호 만날 때 까지 스택 pop
                while (stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            } else if (isAlpa(ch)) {   // 알바펫일 경우, sb에 추가
                sb.append(ch);
            } else {    // 연산자일 경우, 우선 순위 비교
                if (!stack.isEmpty() && priority.get(ch) > priority.get(stack.peek())) {    // 연산자 우선순위가 높으면, 스택에 연산자 push
                    stack.push(ch);
                } else {
                    while (!stack.isEmpty() && priority.get(ch) <= priority.get(stack.peek())) {
                        sb.append(stack.pop());
                    }
                    stack.push(ch);
                }
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return new String(sb);
    }

    static boolean isAlpa(char ch) {
        return ch >= 'A' && ch <= 'Z' ? true : false;
    }

}
