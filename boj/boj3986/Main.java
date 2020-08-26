package boj.boj3986;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int N;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());

        for (int i = 0; i < N; i++) {
            Stack<Character> stack = new Stack<>();
            char[] chars = br.readLine().toCharArray();
            for (int l = 0; l < chars.length; l++) {
                if (!stack.isEmpty() && stack.peek() == chars[l]) {
                    stack.pop();
                } else {
                    stack.push(chars[l]);
                }
            }
            if (stack.isEmpty()) res++;
        }
        System.out.println(res);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
