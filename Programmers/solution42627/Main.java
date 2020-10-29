package Programmers.solution42627;

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
        int answer = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < jobs.length; i++) {
            int start = jobs[i][0];
            int workingTime = jobs[i][1];
            pq.add(new Pair(start, workingTime));
        }
        int len = pq.size();
        int curTime = 0;
        while (!pq.isEmpty()) {
            Pair tmp = pq.poll();
            int start = tmp.start;
            int workingTime = tmp.workingTime;
            if (start > curTime) {
                curTime = start;
            }
            int endTime = curTime + workingTime;
            int totalTime = endTime - start;
            answer += totalTime;
            curTime = endTime;
        }
        return answer / len;
    }
}

class Pair implements Comparable<Pair>{
    int start, workingTime;

    public Pair(int start, int workingTime) {
        this.start = start;
        this.workingTime = workingTime;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.workingTime == o.workingTime) {
            return Integer.compare(this.start, o.start);
        }
        return Integer.compare(this.workingTime, o.workingTime);
    }
}