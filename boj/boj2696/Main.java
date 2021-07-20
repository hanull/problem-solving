package boj.boj2696;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        while (T-- > 0) {
            int M = stoi(br.readLine());
            StringBuilder answer = new StringBuilder();
            StringTokenizer st = null;
            PriorityQueue<Integer> leftQueue = new PriorityQueue<>(((o1, o2) -> o2 - o1));
            PriorityQueue<Integer> rightQueue = new PriorityQueue<>();
            answer.append(M / 2 + 1 + "\n");
            for (int i = 0; i < M; i++) {
                if (i % 10 == 0) st = new StringTokenizer(br.readLine());
                int number = stoi(st.nextToken());

                if (i % 2 == 0) {
                    leftQueue.add(number);
                } else {
                    rightQueue.add(number);
                }

                if (!rightQueue.isEmpty() && leftQueue.peek() > rightQueue.peek()) {
                    int l = leftQueue.poll();
                    int r = rightQueue.poll();
                    leftQueue.add(r);
                    rightQueue.add(l);
                }

                if (i != 0 && i % 20 == 0) answer.append("\n");
                if (i % 2 == 0) answer.append(leftQueue.peek() + " ");
            }
            System.out.println(answer);
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
