package Test.woowa.test4;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int[][] board = {{3, 5, 6}, {9, 2, 7}, {4, 1, 8}};
        System.out.println(sol.solution(n, board));
    }
}

class Solution {

    static int startX = 0;
    static int startY = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(int n, int[][] board) {
        int answer = 0;
        for (int i = 0; i < n * n; i++) {
            answer += bfs(i + 1, n, board);
        }
        return answer;
    }

    private int bfs(int findNum, int n, int[][] board) {
        int res = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(startX, startY, 0));
        boolean[][] visited = new boolean[n][n];
        visited[startX][startY] = true;
        while (!q.isEmpty()) {
            Pair tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int cnt = tmp.cnt;
            if (board[tx][ty] == findNum) {
                startX = tx;
                startY = ty;
                res = cnt + 1;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                nx = fixPoint(nx, n);
                ny = fixPoint(ny, n);
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new Pair(nx, ny, cnt + 1));
            }
        }
        return res;
    }

    private int fixPoint(int point, int n) {
        if (point < 0) {
            return n - 1;
        } else if (point == n) {
            return 0;
        }
        return point;
    }
}

class Pair {
    int x, y, cnt;

    public Pair(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
