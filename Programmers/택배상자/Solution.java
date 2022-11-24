package Programmers.택배상자;

import java.util.Stack;

public class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int index = 0;
        int boxNumber = 1;
        Stack<Integer> stack = new Stack<>();
        while (true) {
            if (!stack.isEmpty() && order[index] == stack.peek()) {
                answer++;
                index++;
                stack.pop();
                continue;
            }

            if (boxNumber > order.length) {
                break;
            }

            if (order[index] == boxNumber) {
                answer++;
                boxNumber++;
                index++;
                continue;
            }

            stack.add(boxNumber);
            boxNumber++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{4, 3, 1, 2, 5}));
        System.out.println(sol.solution(new int[]{5, 4, 3, 2, 1}));
        System.out.println(sol.solution(new int[]{1, 5, 4, 3, 2}));
        System.out.println(sol.solution(new int[]{1, 2, 3, 4, 5}));
    }
}
