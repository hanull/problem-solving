package boj.boj2812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken());
        String input = br.readLine();
        char[] numbers = input.toCharArray();

        Stack<Character> stack = new Stack<>();
        int cnt = K;
        for (int i = 0; i < N; i++) {
            if (!stack.isEmpty()) {
                if (numbers[i] > stack.peek()) {
                    while (!stack.isEmpty() && cnt > 0 && numbers[i] > stack.peek()) {
                        stack.pop();
                        cnt--;
                    }
                }
            }
            stack.add(numbers[i]);
        }
        while (stack.size() > N - K) {
            stack.pop();
        }
        char[] answer = new char[stack.size()];
        int len = stack.size();
        for (int i = len - 1; i >= 0; i--) {
            answer[i] = stack.pop();
        }
        System.out.println(new String(answer));
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
