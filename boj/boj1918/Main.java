package boj.boj1918;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] input = sc.next().toCharArray();
        System.out.println(infix_to_prefix(input));
    }

    private static String infix_to_prefix(char[] input) {
        int len = input.length;
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int idx = 0;

        while (idx < len) {
            // 알파벳 일 때, result에 출력
            if (input[idx] >= 'A' && input[idx] <= 'Z') {
                result.append(input[idx]);
            }
            // 연산자 일 때, stack에 push
            else if (input[idx] == '(') {
                stack.push(input[idx]);
            } else if (input[idx] == ')') {
                pop_operator(result, stack);
            } else if (!stack.isEmpty() && priority(stack.peek()) >= priority(input[idx])) {
                set_operator(result, stack, priority(input[idx]));
                stack.push(input[idx]);
            } else {
                stack.push(input[idx]);
            }
            idx++;
        }
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    private static void pop_operator(StringBuilder result, Stack<Character> stack) {
        while (!stack.isEmpty() && stack.peek() != '(') {
            result.append(stack.pop());
        }
        stack.pop();
    }

    private static void set_operator(StringBuilder result, Stack<Character> stack, int input_priority) {
        while (!stack.isEmpty() && priority(stack.peek()) >= input_priority) {
            result.append(stack.pop());
        }
    }

    private static int priority(char input) {
        if (input == '*' || input == '/') {
            return 2;
        } else if (input == '+' || input == '-') {
            return 1;
        } else if (input == '('){
            return 0;
        }
        return -1;
    }

}
