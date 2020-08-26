package boj.boj1935;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    static int N;
    static int[] alpa;
    static char[] operator;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        operator = sc.next().toCharArray();

        alpa = new int[N];
        for (int i = 0; i < N; i++) {
            alpa[i] = sc.nextInt();
        }
        System.out.println(cal());
    }

    private static String cal() {
        double res = 0;
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < operator.length; i++) {
            if (operator[i] >= 'A' && operator[i] <= 'Z') {
                stack.push((double) alpa[operator[i] - 'A']);
            } else {
                Double tmp1 = stack.pop();
                Double tmp2 = stack.pop();
                double total = 0;
                if (operator[i] == '*') {
                    total = tmp2 * tmp1;
                }
                if (operator[i] == '+') {
                    total = tmp2 + tmp1;
                }
                if (operator[i] == '/') {
                    total = tmp2 / tmp1;
                }
                if (operator[i] == '-') {
                    total = tmp2 - tmp1;
                }
                stack.push(total);
            }
        }
        return setResult(stack.pop());
    }

    private static String setResult(Double input) {
        return String.format("%.2f", input);
    }
}
