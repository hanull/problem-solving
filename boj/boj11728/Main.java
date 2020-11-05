package boj.boj11728;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = stoi(st.nextToken());
            pq.offer(num);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = stoi(st.nextToken());
            pq.offer(num);
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll() + " ");
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }

}
