package boj.boj1715;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int N = stoi(br.readLine());

        for (int i = 0; i < N; i++) {
            int x = stoi(br.readLine());
            pq.add(x);
        }
        sb.append(getTotal(pq));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int getTotal(PriorityQueue<Integer> pq) {
        int total = 0;

        if (pq.size() == 1) {
            return 0;
        }
        while (true) {
            if (pq.size() == 1) {
                break;
            }
            int x = pq.poll();
            int y = pq.poll();
            int tmp = x + y;
            pq.add(tmp);
            total += tmp;
        }
        return total;
    }

    private static int cal(Stack<Integer> stack) {
        int total = 0;
        while (!stack.isEmpty()) {
            total += stack.pop();
        }
        return total;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
