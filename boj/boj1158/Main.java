package boj.boj1158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken());
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            q.add(i);
        }
        StringBuilder sb = new StringBuilder("<");
        while (!q.isEmpty()) {
            int cnt = 0;
            while (cnt++ < K - 1) {
                q.add(q.poll());
            }
            sb.append(q.poll());
            if (q.size() > 0) sb.append(", ");
        }
        sb.append(">");
        System.out.println(sb);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
