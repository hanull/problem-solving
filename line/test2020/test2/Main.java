package line.test2;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] ball = {11, 2, 9, 13, 24};
        int[] order = {9, 2, 13, 24, 11};
        System.out.println(Arrays.toString(sol.solution(ball, order)));
    }
}

class Solution {
    public int[] solution(int[] ball, int[] order) {
        Deque<Integer> deq = new LinkedList<>();
        Queue<Integer> tmpQue = new LinkedList<>();
        int[] res = new int[ball.length];
        int idx = 0;

        for (int i = 0; i < ball.length; i++) {
            deq.add(ball[i]);
        }
        for (int i = 0; i < order.length; i++) {
            int first = deq.peekFirst();
            int last = deq.peekLast();
            int target = order[i];
            if (!tmpQue.isEmpty()) {
                int t = 0;
                int len = tmpQue.size();
                while (t <= len) {
                    int check = tmpQue.poll();
                    if (check == first) {
                        res[idx++] = check;
                        deq.pollFirst();
                        first = deq.peekFirst();
                        t = 0;
                        len--;
                    } else if (check == last) {
                        res[idx++] = check;
                        deq.pollLast();
                        last = deq.peekLast();
                        t = 0;
                        len--;
                    } else {
                        tmpQue.add(check);
                    }
                    t++;
                }
            }
            if (first == target) {
                res[idx++] = target;
                deq.pollFirst();
            } else if (last == target) {
                res[idx++] = target;
                deq.pollLast();
            } else {
                tmpQue.add(target);
            }
        }
        return res;
    }
}
