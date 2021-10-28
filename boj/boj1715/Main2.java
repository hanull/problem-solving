package boj.boj1715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        if (N == 1) {
            System.out.println(0);
            System.exit(0);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(stoi(br.readLine()));
        }
        int answer = 0;
        while (!pq.isEmpty()) {
            int a = pq.poll();
            int b = pq.poll();
            int total = a + b;
            answer += total;
            if (pq.isEmpty()) break;
            pq.add(total);
        }
        System.out.println(answer);
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
