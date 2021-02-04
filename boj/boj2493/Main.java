package boj.boj2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] building;
    static int[] status;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        building = new int[N];
        status = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            building[i] = stoi(st.nextToken());
        }

        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && building[stack.peek()] < building[i]) {
                status[stack.pop()] = i + 1;
            }
            stack.push(i);
        }
        for (int idx : status) {
            System.out.print(idx + " ");
        }

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
