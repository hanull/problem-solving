package boj.boj11286;

import java.io.*;
import java.util.PriorityQueue;

class Pair implements Comparable<Pair>{
    int absVal;
    int originVal;

    public Pair(int absVal, int originVal) {
        this.absVal = absVal;
        this.originVal = originVal;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.absVal == o.absVal) {
            return Integer.compare(this.originVal, o.originVal);
        }
        return Integer.compare(this.absVal, o.absVal);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = stoi(br.readLine());
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int x = stoi(br.readLine());
            if (x == 0) {
                sb.append(printMinValue(pq) + "\n");
            } else {
                pq.add(new Pair(Math.abs(x), x));
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int printMinValue(PriorityQueue<Pair> pq) {
        if (pq.isEmpty()) {
            return 0;
        } else {
            return pq.poll().originVal;
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
