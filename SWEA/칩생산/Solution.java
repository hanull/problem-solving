package SWEA.칩생산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int N, M, chipCount;
    static int[] dp;
    static List<Node> nodeList;
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
        StringBuilder answer = new StringBuilder();
        int T = stoi(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());
            int[][] originMap = new int[N][M];
            nodeList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    originMap[i][j] = stoi(st.nextToken());
                    if (originMap[i][j] == 0) nodeList.add(new Node(i, j));
                }
            }
            dp = new int[nodeList.size()];
            chipCount = 0;
            dfs(0, 0, originMap);
            answer.append("#").append(tc).append(" ").append(chipCount).append("\n");
        }
        System.out.print(answer);
    }

    private static void dfs(int index, int count, int[][] originMap) {
        if (index == nodeList.size()) {
            chipCount = Math.max(chipCount, count);
            return;
        }
        if (dp[index] != 0 && dp[index] > count) return;
        if (isPossible(nodeList.get(index), originMap)) {
            setChip(nodeList.get(index), 1, originMap);
            dfs(index + 1, count + 1, originMap);
            setChip(nodeList.get(index), 0, originMap);
            if (dp[index] < count) dp[index] = count;
        }
        dfs(index + 1, count, originMap);
    }

    private static void setChip(Node node, int num, int[][] originMap) {
        int tx = node.x;
        int ty = node.y;
        originMap[tx][ty] = originMap[tx][ty+1] = originMap[tx+1][ty] = originMap[tx+1][ty+1] = num;
    }

    private static boolean isPossible(Node node, int[][] originMap) {
        int tx = node.x;
        int ty = node.y;
        if (tx + 1 >= N || ty + 1 >= M) return false;
        if (originMap[tx][ty] == 1 || originMap[tx][ty+1] == 1 || originMap[tx+1][ty] == 1 || originMap[tx+1][ty+1] == 1) return false;
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
