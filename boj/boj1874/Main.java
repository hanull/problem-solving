package boj.boj1874;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (int i = 1; i <= n; i++) {
            stack.push(i);
            sb.append("+\n");
            while (!stack.isEmpty() && stack.peek() == input[j]) {
                stack.pop();
                sb.append("-\n");
                j++;
            }
        }
        if (!stack.isEmpty()) {
            System.out.println("NO");
        } else {
            System.out.println(sb.toString());
        }
    }
}
