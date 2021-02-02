package boj.boj2346;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Deque<Balloon> deque = new ArrayDeque<>();

    static class Balloon {
        int idx, num;

        public Balloon(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            deque.addLast(new Balloon(i + 1, stoi(st.nextToken())));
        }

        StringBuilder sb = new StringBuilder();
        sb.append(1 + " ");
        int num = deque.pollFirst().num;
        for (int i=1; i<N; i++) {
            if (deque.isEmpty()) break;
            if (num < 0) {
                while (num++ < 0) {
                    deque.addFirst(deque.pollLast());
                }
            } else {
                num--;
                while (num-- > 0) {
                    deque.addLast(deque.pollFirst());
                }
            }
            num = deque.peekFirst().num;
            sb.append(deque.pollFirst().idx + " ");
        }
        System.out.println(sb);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
