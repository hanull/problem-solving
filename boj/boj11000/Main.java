package boj.boj11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static PriorityQueue<Work> pq = new PriorityQueue<>(new Comparator<Work>() {
        @Override
        public int compare(Work o1, Work o2) {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        }
    });

    static PriorityQueue<Work> tempQ = new PriorityQueue<>(new Comparator<Work>() {
        @Override
        public int compare(Work o1, Work o2) {
            return o1.end - o2.end;
        }
    });

    static class Work {
        int start;
        int end;

        public Work(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Work(stoi(st.nextToken()), stoi(st.nextToken())));
        }

        while (!pq.isEmpty()) {
            Work tmp = pq.poll();
            if (tempQ.isEmpty()) {
                tempQ.add(tmp);
            } else {
                if (tmp.start >= tempQ.peek().end) {
                    tempQ.poll();
                }
                tempQ.add(tmp);
            }
        }
        System.out.println(tempQ.size());
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
