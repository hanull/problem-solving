package boj.boj2251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    static int A, B, C;

    static class Node {
        int a, b, c;

        public Node(final int a, final int b, final int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        bfs();
    }

    private static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[201][201][201];
        q.add(new Node(0, 0, C));
        visited[0][0][C] = true;
        TreeSet<Integer> treeSet = new TreeSet<>();

        while (!q.isEmpty()) {
            final Node node = q.poll();
            int ta = node.a;
            int tb = node.b;
            int tc = node.c;

            if (ta == 0) {
                treeSet.add(tc);
            }

            // a -> b
            if (ta + tb > B) {
                if (!visited[ta + tb - B][B][tc]) {
                    q.add(new Node(ta + tb - B, B, tc));
                    visited[ta + tb - B][B][tc] = true;
                }
            } else {
                if (!visited[0][ta + tb][tc]) {
                    q.add(new Node(0, ta + tb, tc));
                    visited[0][ta + tb][tc] = true;
                }
            }

            // a -> c
            if (ta + tc > C) {
                if (!visited[ta + tc - C][tb][C]) {
                    q.add(new Node(ta + tc - C, tb, C));
                    visited[ta + tc - C][tb][C] = true;
                }
            } else {
                if (!visited[0][tb][ta + tc]) {
                    q.add(new Node(0, tb, ta + tc));
                    visited[0][tb][ta + tc] = true;
                }
            }

            // b -> a
            if (ta + tb > A) {
                if (!visited[A][ta + tb - A][tc]) {
                    q.add(new Node(A, ta + tb - A, tc));
                    visited[A][ta + tb - A][tc] = true;
                }
            } else {
                if (!visited[ta + tb][0][tc]) {
                    q.add(new Node(ta + tb, 0, tc));
                    visited[ta + tb][0][tc] = true;
                }
            }

            // b -> c
            if (tb + tc > C) {
                if (!visited[ta][tb + tc - C][C]) {
                    q.add(new Node(ta, tb + tc - C, C));
                    visited[ta][tb + tc - C][C] = true;
                }
            } else {
                if (!visited[ta][0][tb + tc]) {
                    q.add(new Node(ta, 0, tb + tc));
                    visited[ta][0][tb + tc] = true;
                }
            }

            // c -> a
            if (ta + tc > A) {
                if (!visited[A][tb][ta + tc - A]) {
                    q.add(new Node(A, tb, ta + tc - A));
                    visited[A][tb][ta + tc - A] = true;
                }
            } else {
                if (!visited[ta + tc][tb][0]) {
                    q.add(new Node(ta + tc, tb, 0));
                    visited[ta + tc][tb][0] = true;
                }
            }

            // c -> b
            if (tc + tb > B) {
                if (!visited[ta][B][tc + tb - B]) {
                    q.add(new Node(ta, B, tc + tb - B));
                    visited[ta][B][tc + tb - B] = true;
                }
            } else {
                if (!visited[ta][tc + tb][0]) {
                    q.add(new Node(ta, tc + tb, 0));
                    visited[ta][tc + tb][0] = true;
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        int index = 0;
        int last = treeSet.size();
        for (final Integer number : treeSet) {
            answer.append(number);
            if (index < last) {
                answer.append(" ");
            }
            index++;
        }
        System.out.print(answer);
    }
}

