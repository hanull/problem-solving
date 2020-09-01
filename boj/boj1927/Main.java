package boj.boj1927;

import java.io.*;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = stoi(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int x = stoi(br.readLine());
            if (x == 0) {
                sb.append(printMinValue(pq) + "\n");
            } else {
                pq.add(x);
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int printMinValue(PriorityQueue<Integer> pq) {
        if (pq.isEmpty()) {
            return 0;
        } else {
            return pq.poll();
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
