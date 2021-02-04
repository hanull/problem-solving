package SWEA.swea1218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Solution {

    static int N;
    static char[] input;
    static Stack<Character> stack;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashMap<Character, Character> hashMap = new HashMap<>();

    static {
        hashMap.put(')', '(');
        hashMap.put(']', '[');
        hashMap.put('}', '{');
        hashMap.put('>', '<');
    }

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= 10; tc++) {
            N = stoi(br.readLine());
            input = br.readLine().toCharArray();
            stack = new Stack<>();

            int flag = 1;
            for (int i = 0; i < input.length; i++) {
                if (input[i] == '(' || input[i] == '[' || input[i] == '{' || input[i] == '<') {
                    stack.add(input[i]);
                } else {
                    if (stack.peek() == hashMap.get(input[i])) {
                        stack.pop();
                    } else {
                        flag = 0;
                        break;
                    }
                }
            }
            System.out.println("#" + tc + " " + flag);
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
