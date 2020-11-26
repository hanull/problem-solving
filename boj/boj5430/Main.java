package boj.boj5430;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = stoi(br.readLine());
        while (T-- > 0) {
            String func = br.readLine();
            int n = stoi(br.readLine());
            String tmp = br.readLine();
            String[] arr = tmp.substring(1, tmp.length() - 1).split(",");
            Deque<String> deque = new LinkedList<>();
            for (int i = 0; i < arr.length; i++) {
                deque.add(arr[i]);
            }
            boolean error = false;
            boolean order = false;
            for (int i = 0; i < func.length(); i++) {
                char p = func.charAt(i);
                if (p == 'R') {
                    order = order ? false : true;
                } else {
                    if (deque.isEmpty() || deque.peek().equals("")) {
                        error = true;
                        break;
                    }
                    if (order) {
                        deque.pollLast();
                    } else {
                        deque.pollFirst();
                    }
                }
            }
            if (error) {
                bw.write("error");
            } else {
                bw.write("[");
                StringBuilder sb = new StringBuilder();
                if (order) {
                    while (!deque.isEmpty()) {
                        sb.append(deque.pollLast());
                        if (deque.size() >= 1) sb.append(",");
                    }
                } else {
                    while (!deque.isEmpty()) {
                        sb.append(deque.pollFirst());
                        if (deque.size() >= 1) sb.append(",");
                    }
                }
                bw.write(sb.toString());
                bw.write("]");
            }
            bw.write("\n");
        }
        bw.close();
        br.close();
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
