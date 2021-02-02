package boj.boj5397;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());

        while (T-- > 0) {
            Deque<Character> leftDeq = new LinkedList<>();
            Deque<Character> rightDeq = new LinkedList<>();
            String input = br.readLine();

            for (char ch : input.toCharArray()) {
                if (ch == '<') {
                    if (!leftDeq.isEmpty()) rightDeq.addFirst(leftDeq.pollLast());
                } else if (ch == '>') {
                    if (!rightDeq.isEmpty()) leftDeq.addLast(rightDeq.pollFirst());
                } else if (ch == '-') {
                    if (!leftDeq.isEmpty()) leftDeq.pollLast();
                } else {
                    leftDeq.addLast(ch);
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!leftDeq.isEmpty()) {
                sb.append(leftDeq.pollFirst());
            }
            while (!rightDeq.isEmpty()) {
                sb.append(rightDeq.pollFirst());
            }
            System.out.println(sb);

        }

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
