package boj.boj10828;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        while (N-- > 0) {
            String command = sc.next();
            switch (command) {
                case "push":
                    stack.push(sc.nextInt());
                    break;
                case "pop":
                    if (stack.isEmpty()) sb.append(-1 + "\n");
                    else sb.append(stack.pop() + "\n");
                    break;
                case "size":
                    sb.append(stack.size() + "\n");
                    break;
                case "empty":
                    if (stack.isEmpty()) sb.append(1 + "\n");
                    else sb.append(0 + "\n");
                    break;
                case "top":
                    if (stack.isEmpty()) sb.append(-1 + "\n");
                    else sb.append(stack.peek() + "\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
