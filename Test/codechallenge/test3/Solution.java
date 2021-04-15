package Test.codechallenge.test3;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    static List<Integer>[] vertexList;
    static int[][] dp;
    static boolean[] visited;

    public static long solution(int[] a, int[][] edges) {
        long answer = 0;
        int len = a.length;
        int total = 0;
        boolean flag = false;
        int idx = 0;
        int max = 0;
        dp = new int[300000][30];
        visited = new boolean[len];

        for (int i = 0; i < len; i++) {
            if (a[i] > 0) flag = true;
            if (a[i] > max) idx = i;
            total += a[i];
        }
        if (!flag) return 0;
        if (total != 0) return -1;

        vertexList = new ArrayList[len];
        for (int i = 0; i < len; i++) {
            vertexList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            vertexList[from].add(to);
            vertexList[to].add(from);
        }

        visited[idx] = true;

        return answer;
    }

    public static void main(String[] args) {
        int[] a = {-5, 0, 2, 1, 2};
        int[][] edges = {{0,1}, {3,4}, {2,3}, {0,3}};
        System.out.println(Solution.solution(a, edges));
    }
}
