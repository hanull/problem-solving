package boj.boj14496;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, startNumber, targetNumber;
    static int[] changeCount;
    static List<Integer>[] vertexList;
    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        startNumber = stoi(st.nextToken());
        targetNumber = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        vertexList = new ArrayList[N + 1];
        changeCount = new int[N + 1];
        Arrays.fill(changeCount, MAX);
        for (int i = 1; i <= N; i++) {
            vertexList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            vertexList[from].add(to);
            vertexList[to].add(from);
        }

        solve();
        System.out.println(changeCount[targetNumber] == MAX ? -1 : changeCount[targetNumber]);
    }

    static void solve() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(startNumber);
        changeCount[startNumber] = 0;

        while (!deque.isEmpty()) {
            int curNumber = deque.poll();
            if (curNumber == targetNumber) break;
            for (int nextNumber : vertexList[curNumber]) {
                if (changeCount[nextNumber] > changeCount[curNumber] + 1) {
                    changeCount[nextNumber] = changeCount[curNumber] + 1;
                    deque.add(nextNumber);
                }
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
