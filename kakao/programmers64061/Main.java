package kakao.programmers64061;

import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] move = {1, 5, 3, 5, 1, 2, 1, 4};

        System.out.println(sol.solution(board,move));
    }

}
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int len = board.length;
        int size = board[0].length;
        ArrayList<Stack<Integer>> stacks = new ArrayList<>();
        Stack<Integer> bucket = new Stack<>();

        for (int i = 0; i < size; i++) {
            stacks.add(new Stack<Integer>());
            for (int j = len - 1; j >= 0; j--) {
                if (board[j][i] != 0) {
                    stacks.get(i).add(board[j][i]);
                }
            }
        }
        for (int i = 0; i < moves.length; i++) {
            if (stacks.get(moves[i] - 1).isEmpty()) continue;
            int newOne = stacks.get(moves[i] - 1).pop();
            if (bucket.isEmpty()) {
                bucket.add(newOne);
            } else if (check(newOne, bucket.peek())) {
                bucket.pop();
                answer += 2;
            } else {
                bucket.push(newOne);
            }
        }
        return answer;
    }

    private boolean check(int newOne, Integer top) {
        return newOne == top ? true : false;
    }
}
