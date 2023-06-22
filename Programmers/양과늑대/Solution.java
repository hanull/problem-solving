package Programmers.양과늑대;

import java.util.*;

public class Solution {

    private static int answer;

    public int solution(int[] info, int[][] edges) {
        List<Integer>[] nodes = new List[info.length];
        for (int i = 0; i < info.length; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            final int from = edge[0];
            final int to = edge[1];
            nodes[from].add(to);
            nodes[to].add(from);
        }

        dfs(0, 0, 0, nodes, info, new boolean[info.length][info.length + 1][info.length + 1]);

        return answer;
    }

    private void dfs(final int currentNode, int sheepCount, int wolfCount, final List<Integer>[] nodes,
                     final int[] info, final boolean[][][] visited) {
        if (info[currentNode] == 0) {
            sheepCount++;
        } else if (info[currentNode] == 1){
            wolfCount++;
        }

        if (sheepCount <= wolfCount) {
            return;
        }

        answer = Math.max(answer, sheepCount);

        for (int i = 0; i < nodes[currentNode].size(); i++) {
            final int next = nodes[currentNode].get(i);
            final int temp = info[currentNode];
            if (visited[next][sheepCount][wolfCount]) {
                continue;
            }
            visited[next][sheepCount][wolfCount] = true;
            info[currentNode] = -1;
            dfs(next, sheepCount, wolfCount, nodes, info, visited);
            visited[next][sheepCount][wolfCount] = false;
            info[currentNode] = temp;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}}));
    }
}
