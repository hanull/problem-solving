package boj.boj2644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static int startPoint, endPoint;
    static int result = -1;
    static boolean[] visited;
    static List<Integer>[] vertexList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        startPoint = stoi(st.nextToken());
        endPoint = stoi(st.nextToken());
        vertexList = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            vertexList[i] = new ArrayList<>();
        }

        int M = stoi(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            vertexList[from].add(to);
            vertexList[to].add(from);
        }

        dfs(0, startPoint);
        System.out.println(result);
    }

    static void dfs(int cnt, int vertex) {
        if (vertex == endPoint) {
            result = cnt;
            return;
        }

        visited[vertex] = true;
        for (int next : vertexList[vertex]) {
            if (!visited[next]) {
                dfs(cnt + 1, next);
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
