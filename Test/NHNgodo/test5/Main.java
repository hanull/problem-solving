package Test.NHNgodo.test5;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] votes = {5, 7, 7};
        System.out.println(sol.solution(votes));
    }
}

//핵심 소스코드의 설명을 주석으로 작성하면 평가에 큰 도움이 됩니다.
class Solution{
    public int solution(int[] votes){
        int len = votes.length;
        if (len == 1) return 0;
        int president = votes[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        for (int i = 1; i < len; i++) {
            pq.add(votes[i]);
        }
        int cnt = 0;
        while (president <= pq.peek()) {
            int max = pq.poll();
            max--;
            pq.add(max);
            cnt++;
            president++;
        }
        return cnt;
    }
}