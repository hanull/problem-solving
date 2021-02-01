package boj.boj10845;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        int N = stoi(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push":
                    q.add(stoi(st.nextToken()));
                    break;
                case "pop":
                    if (q.isEmpty()) {
                        sb.append(-1 + "\n");
                    } else {
                        sb.append(q.poll() + "\n");
                    }
                    break;
                case "front":
                    if (q.isEmpty()) {
                        sb.append(-1 + "\n");
                    } else {
                        sb.append(q.peek() + "\n");
                    }
                    break;
                case "back":
                    if (q.isEmpty()) {
                        sb.append(-1 + "\n");
                    } else {
                        Iterator<Integer> iterator = q.iterator();
                        int num = 0;
                        while (iterator.hasNext()) {
                            num = iterator.next();
                        }
                        sb.append(num + "\n");
                    }
                    break;
                case "size":
                    sb.append(q.size() + "\n");
                    break;
                case "empty":
                    int flag = q.isEmpty() ? 1 : 0;
                    sb.append(flag + "\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
