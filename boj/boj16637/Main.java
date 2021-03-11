package boj.boj16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int max = Integer.MIN_VALUE;
    static int[] number;
    static char[] operator;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        char[] input = br.readLine().toCharArray();
        number = new int[N / 2 + 1];
        operator = new char[N / 2];

        int j = 0;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                number[i / 2] = input[i] - '0';
            } else {
                operator[j++] = input[i];
            }
        }

        findMaxValue(0, number[0]);
        System.out.println(max);
    }

    static void findMaxValue(int idx, int total) {
        if (idx == N / 2) {
            max = Math.max(max, total);
            return;
        }

        // 괄호 x
        findMaxValue(idx + 1, cal(total, number[idx + 1], operator[idx]));

        // 괄호 O
        if (idx <= N / 2 - 2) {
            int tmp = cal(number[idx + 1], number[idx + 2], operator[idx + 1]);
            findMaxValue(idx + 2, cal(total, tmp, operator[idx]));
        }

    }

    static int cal(int a, int b, char op) {
        int res = 0;
        switch (op) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '*':
                res = a * b;
                break;
        }
        return res;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
