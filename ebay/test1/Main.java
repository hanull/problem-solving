package ebay.test1;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] arr = {{2, 5}, {3, 7}, {10, 11}};
        System.out.println(sol.solution(arr));
    }
}

class Solution {
    public int solution(int[][] flowers) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < flowers.length; i++) {
            for (int j = flowers[i][0]; j < flowers[i][1]; j++) {
                hashSet.add(j);
            }
        }
        return hashSet.size();
    }
}