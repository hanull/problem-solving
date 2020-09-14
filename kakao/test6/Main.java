package kakao.test6;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] board = {{1,0,0,3}, {2,0,0,0}, {0,0,0,2}, {3,0,1,0}};
        System.out.println(sol.solution(board, 1, 0));
    }

}

class Solution {
    static int[] dx = {-1, 1, 0, 0};    // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] board, int r, int c) {
        int answer = 0;

        return answer;
    }
}