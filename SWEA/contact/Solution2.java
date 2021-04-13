package SWEA.contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution2 {

    static int N, startPoint;
    static List<Integer>[] contactList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder result = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int resultVertex = 0;
            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            startPoint = stoi(st.nextToken());
            contactList = new ArrayList[101];
            for (int i=1; i<=100; i++) {
                contactList[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int from = stoi(st.nextToken());
                int to = stoi(st.nextToken());
                if (!contactList[from].contains(to)){ contactList[from].add(to);}
            }

            Deque<Integer> deque = new ArrayDeque<>();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            boolean[] visited = new boolean[101];
            deque.add(startPoint);
            visited[startPoint] = true;
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    int currentVertex = deque.pollFirst();
                    for (int nextVertex : contactList[currentVertex]) {
                        if (visited[nextVertex]) continue;
                        visited[nextVertex] = true;
                        deque.addLast(nextVertex);
                        priorityQueue.add(nextVertex);
                    }
                }
                if (!priorityQueue.isEmpty()) resultVertex = priorityQueue.poll();
                priorityQueue.clear();
            }

            result.append("#").append(tc).append(" ").append(resultVertex).append("\n");
        }
        System.out.print(result);
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
