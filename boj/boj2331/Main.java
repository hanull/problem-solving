package boj.boj2331;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        stack.push(sc.nextInt());
        int p = sc.nextInt();

        while (true) {
            int num = cal(stack.peek(), p);
            if (stack.contains(num)) {
                int tmp = 0;
                while ((tmp = (stack.pop())) != num) {
                }
                break;
            }
            stack.push(num);
        }
        System.out.println(stack.size());

    }

    static int cal(int num, int p) {
        int total = 0;
        while (num != 0) {
            int remainder = num % 10;
            num /= 10;
            total += Math.pow(remainder, p);
        }
        return total;
    }

}
