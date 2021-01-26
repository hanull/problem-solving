package boj.boj10773;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        int K = sc.nextInt();
        int total = 0;

        while (K-- > 0) {
            int num = sc.nextInt();
            if (num == 0) {
                total -= stack.pop();
            } else {
                stack.push(num);
                total += num;
            }
        }
        System.out.println(total);
    }
}
