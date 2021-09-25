package Programmers.프렌즈4블록;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    static int[] dx = {0, 0, 1, 1};
    static int[] dy = {0, 1, 0, 1};
    static char[][] map;
    static Queue<Node> q = new LinkedList<>();
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }
        while (isPossible(m, n)) {
            answer += explode();
            down(m, n);
        }
        return answer;
    }

    private boolean isPossible(int n, int m) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                boolean flag = true;
                if (map[i][j] == 'x') continue;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (map[i][j] != map[nx][ny]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        q.add(new Node(nx, ny));
                    }
                }
            }
        }
        return q.size() != 0;
    }

    private void down(int n, int m) {
        Stack<Character> stack = new Stack<>();
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (map[i][j] != 'x') {
                    stack.add(map[i][j]);
                    map[i][j] = 'x';
                }
            }
            int idx = n - 1;
            while (!stack.isEmpty()) {
                map[idx--][j] = stack.pop();
            }
        }
    }

    private int explode() {
        int total = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            if (map[x][y] != 'x') {
                map[x][y] = 'x';
                total++;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] board1 = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        String[] board2 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        System.out.println(sol.solution(4, 5, board1));
        System.out.println(sol.solution(6, 6, board2));
    }
}
