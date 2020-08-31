package boj.boj11004;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());

        PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        for (int i = 0; i < N; i++) {
            q.add(stoi(st.nextToken()));
        }
        int i = 1;
        while (!q.isEmpty()) {
            int res = q.poll();
            if (i == K) {
                bw.write(res + "\n");
                break;
            }
            i++;
        }
        br.close();
        bw.close();
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
