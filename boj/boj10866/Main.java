package boj.boj10866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Deque<Integer> deque = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = stoi(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push_front":
                    deque.addFirst(stoi(st.nextToken()));
                    break;
                case "push_back":
                    deque.addLast(stoi(st.nextToken()));
                    break;
                case "pop_front":
                    if (deque.isEmpty()) sb.append(-1 + "\n");
                    else sb.append(deque.pollFirst() +"\n");
                    break;
                case "pop_back":
                    if (deque.isEmpty()) sb.append(-1 + "\n");
                    else sb.append(deque.pollLast() +"\n");
                    break;
                case "size":
                    sb.append(deque.size() +"\n");
                    break;
                case "empty":
                    if (deque.isEmpty()) sb.append(1 + "\n");
                    else sb.append(0 + "\n");
                    break;
                case "front":
                    if (deque.isEmpty()) sb.append(-1 + "\n");
                    else sb.append(deque.peekFirst() +"\n");
                    break;
                case "back":
                    if (deque.isEmpty()) sb.append(-1 + "\n");
                    else sb.append(deque.peekLast() +"\n");
                    break;
            }
        }
        System.out.println(sb);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
