package boj.boj2504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();
        int res = 0;
        int value = 1;
        boolean flag = true;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                stack.push(input[i]);
                value *= 2;
            } else if (input[i] == '[') {
                stack.push(input[i]);
                value *= 3;
            } else if (input[i] == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    flag = false;
                    break;
                } else {
                    if (input[i - 1] == '(') {
                        res += value;
                    }
                    stack.pop();
                    value /= 2;
                }
            } else if (input[i] == ']'){
                if (stack.isEmpty() || stack.peek() != '[') {
                    flag = false;
                    break;
                } else {
                    if (input[i - 1] == '[') {
                        res += value;
                    }
                    stack.pop();
                    value /= 3;
                }
            }
        }
        if (!flag || !stack.isEmpty()) System.out.println(0);
        else System.out.println(res);
    }
}
