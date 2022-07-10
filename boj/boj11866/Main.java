package boj.boj11866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Deque<Integer> numbers = initNumber(N);
        StringBuilder answer = new StringBuilder("<");
        while (!numbers.isEmpty()) {
            int count = K % numbers.size();
            while (count-- > 0) {
                numbers.addLast(numbers.pollFirst());
            }
            answer.append(numbers.pollLast());
            if (numbers.size() > 0) {
                answer.append(", ");
            }
        }
        answer.append(">");
        System.out.println(answer);
    }

    private static Deque<Integer> initNumber(int n) {
        Deque<Integer> numbers = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
