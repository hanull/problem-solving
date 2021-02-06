package boj.boj1935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        char[] input = br.readLine().toCharArray();
        int[] alpa = new int[26];
        for (int i = 0; i < N; i++) {
            alpa[i] = stoi(br.readLine());
        }

        Stack<Double> stack = new Stack<>();
        for (char ch : input) {
            if (isAlpa(ch)) {
                stack.push((double) alpa[ch - 'A']);
            } else {
                double a = stack.pop();
                double b = stack.pop();
                double res = cal(b, a, ch);
                stack.push(res);
            }
        }
        System.out.printf("%.2f", stack.pop());
    }

    static double cal(double a, double b, char ch) {
        double res = 0;
        switch (ch) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '/':
                res = a / b;
                break;
            case '*':
                res = a * b;
                break;
        }
        return res;
    }

    static boolean isAlpa(char ch) {
        return ch >= 'A' && ch <= 'Z' ? true : false;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}

