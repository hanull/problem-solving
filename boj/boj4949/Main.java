package boj.boj4949;

import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String input = br.readLine();
            if (input.equals(".")) break;

            Stack<Character> stack = new Stack<>();
            char[] arr = input.toCharArray();
            for (char ch : arr) {
                if (isBacket(ch)) {
                    if (ch == '(' || ch == '[') {
                        stack.push(ch);
                    } else if (!stack.isEmpty() && ch == ')' && stack.peek() == '(') stack.pop();
                    else if (!stack.isEmpty() && ch == ']' && stack.peek() == '[') stack.pop();
                    else stack.push(ch);
                }
            }
            if (stack.isEmpty()) {
                bw.write("yes\n");
            } else {
                bw.write("no\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean isBacket(char ch) {
        return ch == '(' || ch == ')' || ch == '[' || ch == ']' ? true : false;
    }
}
