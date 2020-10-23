package Programmers.solution43162;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(sol.solution(n, computers));
    }
}

class Solution {

    public int solution(int n, int[][] computers) {
        int[] network = new int[n];
        int no = 1;
        for (int i = 0; i < n; i++) {
            if (network[i] == 0) {
                dfs(computers, network, i, no, n);
                no++;
            }
        }
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : network) {
            hashSet.add(num);
        }
        return hashSet.size();
    }

    private void dfs(int[][] computers, int[] network, int from, int no, int n) {
        network[from] = no;
        for (int to = 0; to < n; to++) {
            if (from == to) continue;
            if (computers[from][to] == 1 && network[to] == 0) {
                dfs(computers, network, to, no, n);
            }
        }
    }
}