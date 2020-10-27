package Programmers.solution42626;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int k = 7;
        System.out.println(sol.solution(scoville, k));
    }
}

class Solution {

    public int solution(int[] scoville, int K) {
        int res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }
        while (pq.peek() < K) {
            if (pq.size() < 2) return -1;
            int a = pq.poll();
            int b = pq.poll();
            int mix = a + (b * 2);
            pq.add(mix);
            res++;
        }
        return res;
    }
}