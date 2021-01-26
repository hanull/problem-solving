package boj.boj9012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        while (T-- > 0) {
            Stack<Character> stack = new Stack<>();
            char[] arr = br.readLine().toCharArray();
            for (char ch : arr) {
                if (!stack.isEmpty() && ch == ')' && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
            if (stack.isEmpty()) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
