package Programmers.solution42627;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        System.out.println(sol.solution(jobs));
    }
}

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Pair> waitingQue = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.requestTime, o2.requestTime);
            }
        });
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.workingTime, o2.workingTime);
            }
        });
        for (int[] tmp : jobs) {
            int requestTime = tmp[0];
            int workingTime = tmp[1];
            waitingQue.offer(new Pair(requestTime, workingTime));
        }
        int answer = 0;
        int len = waitingQue.size();
        int time = waitingQue.peek().requestTime;
        int cnt = 0;
        while (cnt < len) {
            while (!waitingQue.isEmpty() && waitingQue.peek().requestTime <= time) {
                pq.offer(waitingQue.poll());
            }
            if (!pq.isEmpty()) {
                Pair tmp = pq.poll();
                time += tmp.workingTime;
                answer += time - tmp.requestTime;
                cnt++;
            } else {
                time++;
            }
        }
        return answer / len;
    }
}

class Pair {
    int requestTime, workingTime;

    public Pair(int requestTime, int workingTime) {
        this.requestTime = requestTime;
        this.workingTime = workingTime;
    }

}