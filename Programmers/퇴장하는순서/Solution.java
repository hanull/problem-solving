package Programmers.퇴장하는순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int N, K;
    static List<Integer>[] forwardList, reverseList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        forwardList = new ArrayList[N + 1];
        reverseList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            forwardList[i] = new ArrayList<>();
            reverseList[i] = new ArrayList<>();
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            forwardList[from].add(to);
            reverseList[to].add(from);
        }

        int total = 0;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            boolean flag = true;
            forwardSearch(i);
            reverseSearch(i);
            for (int f=1; f<=N; f++) {
                if (!visited[f]) {
                    flag = false;
                    break;
                }
            }
            if (flag) total++;
        }
        System.out.println(total);

    }

    static void forwardSearch(int start) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(start);
        while (!deque.isEmpty()) {
            int current = deque.poll();
            for (int next : forwardList[current]) {
                if (visited[next]) continue;
                visited[next] = true;
                deque.add(next);
            }
        }
    }

    static void reverseSearch(int start) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(start);
        visited[start] = true;
        while (!deque.isEmpty()) {
            int current = deque.poll();
            for (int next : reverseList[current]) {
                if (visited[next]) continue;
                visited[next] = true;
                deque.add(next);
            }
        }
    }


    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
