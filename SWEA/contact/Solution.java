package SWEA.contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int N, start, max;
    static boolean[] visited;
    static boolean[][] list;
    static Deque<Integer> deque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            start = stoi(st.nextToken());
            visited = new boolean[101];
            list = new boolean[101][101];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i += 2) {
                int from = stoi(st.nextToken());
                int to = stoi(st.nextToken());
                list[from][to] = true;
            }
            max = 0;
            call();
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.print(sb);

    }

    static void call() {
        deque = new ArrayDeque<>();
        deque.add(start);
        visited[start] = true;

        while (!deque.isEmpty()) {
            int size = deque.size();
            PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o2 - o1));
            for (int i = 0; i < size; i++) {
                int from = deque.pollFirst();
                for (int to = 1; to <= 100; to++) {
                    if (list[from][to] && !visited[to]) {
                        visited[to] = true;
                        pq.add(to);
                        deque.add(to);
                    }
                }
            }
            if (!pq.isEmpty()) max = pq.poll();
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
