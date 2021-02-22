package boj.boj17299;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] arr = new int[N + 1];
        int[] countArr = new int[1000001];
        int[] res = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = stoi(st.nextToken());
            arr[i] = num;
            countArr[num] += 1;
        }
        Arrays.fill(res, -1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty() && countArr[arr[stack.peek()]] < countArr[arr[i]]) {
                res[stack.pop()] = arr[i];
            }
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
