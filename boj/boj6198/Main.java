package boj.boj6198;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] height = new int[N];
        for (int i = 0; i < N; i++) {
            height[i] = stoi(br.readLine());
        }

        long total = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.peek() <= height[i]) {  // 볼 수 없는 빌딩을 모두 제거
                stack.pop();
            }
            total += stack.size();  // stack.size() 만큼의 빌링만이 i번째 빌딩을 볼 수 있다
            stack.push(height[i]);  // 볼 수 있는 빌딩
        }
        System.out.println(total);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
