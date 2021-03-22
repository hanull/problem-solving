package boj.boj18352;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, startNumber, targetDistance;
    static int[] vertexDistance;
    static List<Integer>[] vertexList;
    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        targetDistance = stoi(st.nextToken());
        startNumber = stoi(st.nextToken());

        vertexDistance = new int[N + 1];
        Arrays.fill(vertexDistance, MAX);
        vertexList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            vertexList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            vertexList[from].add(to);
        }

        solve();
        printResult();

    }

    static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (vertexDistance[i] == targetDistance) {
                sb.append(i).append("\n");
            }
        }
        System.out.print(sb.length() == 0 ? -1 : sb);
    }

    static void solve() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(startNumber);
        vertexDistance[startNumber] = 0;

        while (!deque.isEmpty()) {
            int currentVertex = deque.poll();
            for (int nextVertex : vertexList[currentVertex]) {
                if (vertexDistance[nextVertex] > vertexDistance[currentVertex] + 1) {
                    vertexDistance[nextVertex] = vertexDistance[currentVertex] + 1;
                    deque.add(nextVertex);
                }
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
