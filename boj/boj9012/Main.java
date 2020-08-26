package boj.boj9012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = stoi(br.readLine());

        for (int i = 0; i < T; i++) {
            Stack<Character> stack = new Stack<>();
            char[] chars = br.readLine().toCharArray();
            for (int l = 0; l < chars.length; l++) {
                if (!stack.isEmpty() && stack.peek() == '(' && chars[l] == ')') {
                    stack.pop();
                } else {
                    stack.push(chars[l]);
                }
            }
            System.out.println(getResult(stack.size()));
        }
    }

    private static String getResult(int size) {
        return size == 0 ? "YES" : "NO";
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
