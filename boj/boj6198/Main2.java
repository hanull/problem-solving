package boj.boj6198;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] building = new int[N];
        for (int i = 0; i < N; i++) {
            building[i] = stoi(br.readLine());
        }
        Stack<Integer> stack = new Stack<>();
        int total = 0;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && building[i] >= stack.peek()) {
                stack.pop();
            }
            total += stack.size();
            stack.push(building[i]);
        }
        System.out.println(total);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
