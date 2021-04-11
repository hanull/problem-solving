package line.test2020.test1;

import java.util.PriorityQueue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] boxes = {{1, 2}, {2, 1}, {3, 3}, {4, 5}, {5, 6}, {7, 8}};
        System.out.println(sol.solution(boxes));
    }
}

class Solution {
    public int solution(int[][] boxes) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < 2; j++) {
                pq.add(boxes[i][j]);
            }
        }
        while (!pq.isEmpty()) {
            if (stack.isEmpty()) {
                stack.push(pq.poll());
            } else {
                int top = stack.pop();
                int newOne = pq.poll();
                if (top != newOne){
                    stack.push(top);
                    stack.push(newOne);
                }

            }
        }
        return stack.size()/2;
    }
}