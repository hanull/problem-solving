package ebay.test1;

import java.util.PriorityQueue;

class Time implements Comparable<Time>{
    int start;
    int end;

    public Time(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Time o) {
        return Integer.compare(this.end, o.end);
    }
}

public class Main {

    public static void main(String[] args) {
        int N = 2;
        int[][] simulation_data = {{0, 3}, {2, 5}, {4, 2}, {5, 3}};
        System.out.println(solution(N, simulation_data));
    }

    private static int solution(int N, int[][] simulation_data) {
        int len = simulation_data.length;
        int res = 0;
        // 창구수가 고객의수 보다 많을 경우, 기다릴 필요가 없다.
        if (N >= len) {
            return res;
        }
        PriorityQueue<Time> q = new PriorityQueue<>();
        int index = 0;
        while (q.size() < N) {
            q.add(new Time(simulation_data[index][0], simulation_data[index][0] + simulation_data[index][1]));
            index++;
        }
        for (int i = index; i < len; i++) {
            int done = q.poll().end;
            int next = simulation_data[i][0];
            int gap = done - next > 0 ? done - next : 0;
            res += gap;
            q.add(new Time(simulation_data[i][0] + gap, simulation_data[i][0] + simulation_data[i][1] + gap));
        }
        return res;
    }

}
