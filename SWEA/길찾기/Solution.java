package SWEA.길찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder result = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            int N = stoi(st.nextToken());
            List<Integer>[] vertex = new ArrayList[100];
            for (int i = 0; i < 100; i++) {
                vertex[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int from = stoi(st.nextToken());
                int to = stoi(st.nextToken());
                vertex[from].add(to);
            }

            Deque<Integer> deque = new ArrayDeque<>();
            boolean[] visited = new boolean[100];
            deque.add(0);
            visited[0] = true;
            boolean flag = false;

            while (!deque.isEmpty()) {
                int currentVertex = deque.poll();
                if (currentVertex == 99) {
                    flag = true;
                    break;
                }
                for (int next : vertex[currentVertex]) {
                    if (visited[next]) continue;
                    visited[next] = true;
                    deque.add(next);
                }
            }

            result.append("#").append(tc).append(" ").append(flag ? 1 : 0).append("\n");
        }
        System.out.print(result);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
