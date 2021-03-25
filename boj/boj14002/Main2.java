package boj.boj14002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] numberArray = new int[N];
        int[] arrayLength = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numberArray[i] = stoi(st.nextToken());
        }

        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            arrayLength[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (numberArray[j] < numberArray[i] && arrayLength[i] < arrayLength[j] + 1) {
                    arrayLength[i] = arrayLength[j] + 1;
                }
            }
            maxLength = Math.max(maxLength, arrayLength[i]);
        }

        System.out.println(maxLength);
        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            if (arrayLength[i] == maxLength) {
                stack.add(numberArray[i]);
                maxLength--;
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
