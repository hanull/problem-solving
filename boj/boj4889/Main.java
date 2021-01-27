package boj.boj4889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int no = 1;
        while (true) {
            String input = br.readLine();
            if (input.contains("-")) break;

            char[] arr = input.toCharArray();
            Stack<Character> stack = new Stack<>();
            int cnt = 0;
            for (char ch : arr) {
                if (ch == '{') {
                    stack.push(ch);
                } else if (!stack.isEmpty()) {  // ch = '}'
                    stack.pop();
                } else {
                    cnt++;
                    stack.push('{');
                }
            }
            sb.append(no+". " + (cnt + stack.size() / 2) + "\n");
            no++;
        }
        System.out.println(sb);
    }
}
