package boj.boj17298;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] arr = new int[N];
        Stack<Integer> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = stoi(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                arr[stack.pop()] = arr[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            arr[stack.pop()] = -1;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num + " ");
        }
        System.out.println(sb);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
