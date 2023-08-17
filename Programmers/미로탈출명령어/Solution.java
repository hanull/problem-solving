package Programmers.미로탈출명령어;

import java.util.*;

public class Solution {

    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static char[] directionsChars = {'d', 'l', 'r', 'u'};

    static class Node {
        int x, y;
        String directions;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
            directions = "";
        }

        public Node(final int x, final int y, final String directions) {
            this.x = x;
            this.y = y;
            this.directions = directions;
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n][m][k + 1];
        q.add(new Node(x - 1, y - 1));
        visited[x - 1][y - 1][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int tx = node.x;
            int ty = node.y;
            String directions = node.directions;
            int dist = directions.length();
            if (tx == r - 1 && ty == c - 1 && dist == k) {
                return directions;
            }
            if (dist >= k) {
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (visited[nx][ny][dist + 1]) {
                    continue;
                }
                visited[nx][ny][dist + 1] = true;
                q.add(new Node(nx, ny, directions + directionsChars[d]));
            }
        }
        return "impossible";
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(3, 4, 2, 3, 3, 1, 5));
        System.out.println(sol.solution(2, 2, 1, 1, 2, 2, 2));
        System.out.println(sol.solution(3, 3, 1, 2, 3, 3, 4));
    }
}
