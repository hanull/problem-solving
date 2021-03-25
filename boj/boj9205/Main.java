package boj.boj9205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static final int possibleDistance = 1000;
    static Node[] vertexList;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean resultFlag;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        while (T-- > 0) {
            N = stoi(br.readLine());
            vertexList = new Node[N + 2];
            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = stoi(st.nextToken());
                int y = stoi(st.nextToken());
                vertexList[i] = new Node(x, y);
            }

            graph = new ArrayList<>();
            for (int i = 0; i < N + 2; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < N + 1; i++) {
                for (int j = i + 1; j < N + 2; j++) {
                    if (isMove(vertexList[i], vertexList[j])) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }

            resultFlag = false;
            bfs();
            System.out.println(resultFlag ? "happy" : "sad");
        }
    }

    static void bfs() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(0);
        boolean[] visited = new boolean[N + 2];
        visited[0] = true;

        while (!deque.isEmpty()) {
            int currentVertex = deque.poll();
            if (currentVertex == N + 1) {
                resultFlag = true;
                return;
            }
            for (int nextVertex : graph.get(currentVertex)) {
                if (visited[nextVertex]) continue;
                visited[nextVertex] = true;
                deque.add(nextVertex);
            }
        }
    }

    static boolean isMove(Node from, Node to) {
        int dist = Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
        return dist <= possibleDistance;
    }
    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
