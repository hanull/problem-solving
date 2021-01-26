package boj.boj1874;

import java.io.*;
import java.util.Stack;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = stoi(br.readLine());
        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = stoi(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        for (int i = 1; i <= N; i++) {
            stack.push(i);
            sb.append("+\n");
            while (!stack.isEmpty() && num[idx] == stack.peek()) {
                stack.pop();
                sb.append("-\n");
                idx++;
            }
        }
        if (!stack.isEmpty()) System.out.println("NO");
        else System.out.println(sb);
        br.close();
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
