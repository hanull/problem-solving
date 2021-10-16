package boj.boj2253;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 987654321;
    static class Node {
        int no, jump;

        public Node(int no, int jump) {
            this.no = no;
            this.jump = jump;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        int[] smallStone = new int[M];
        for (int i = 0; i < M; i++) {
            smallStone[i] = stoi(br.readLine()) - 1;
        }
        int[][] dp = new int[N][200];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], MAX);
        dp[0][1] = 0;
        dp[1][1] = 1;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(1, 1));
        while (!q.isEmpty()) {
            Node node = q.poll();
            int no = node.no;
            int jump = node.jump;
            // 한 칸 이상 점프해야함.
            // 최대 N 범위를 넘어갈 수 없다.
            // 작은 돌로 점프할 수 없다.
            if (no + jump + 1 < N && dp[no + jump + 1][jump + 1] == MAX && isPossible(no + jump + 1, smallStone)) {
                dp[no + jump + 1][jump + 1] = dp[no][jump] + 1;
                q.add(new Node(no + jump + 1, jump + 1));
            }
            if (no + jump < N && dp[no + jump][jump] == MAX && isPossible(no + jump, smallStone)) {
                dp[no + jump][jump] = dp[no][jump] + 1;
                q.add(new Node(no + jump, jump));
            }
            if (no + jump - 1 < N && jump - 1 >= 1 && dp[no + jump - 1][jump - 1] == MAX && isPossible(no + jump - 1, smallStone)) {
                dp[no + jump - 1][jump - 1] = dp[no][jump] + 1;
                q.add(new Node(no + jump - 1, jump - 1));
            }
        }
        int answer = MAX;
        for (int i = 0; i < 200; i++) {
            answer = Math.min(answer, dp[N - 1][i]);
        }
        System.out.println(answer == MAX ? -1 : answer);
    }

    private static boolean isPossible(int no, int[] smallStone) {
        for (int stone : smallStone) {
            if (no == stone) return false;
        }
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
