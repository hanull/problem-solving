package SWEA.방향전환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {

    static int startX, startY, targetX, targetY, min;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y, dist, direction;

        public Node(int x, int y, int dist, int direction) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.direction = direction;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            startX = stoi(st.nextToken()) + 100;
            startY = stoi(st.nextToken()) + 100;
            targetX = stoi(st.nextToken()) + 100;
            targetY = stoi(st.nextToken()) + 100;
            min = Integer.MAX_VALUE;
            bfs();
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.print(sb);
    }

    static void bfs() {
        Deque<Node> deque = new ArrayDeque<>();
        boolean[][][] visited = new boolean[201][201][2];
        deque.add(new Node(startX, startY, 0, 0));
        deque.add(new Node(startX, startY, 0, 1));

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            int dist = tmp.dist;
            int direction = tmp.direction;
            if (tx == targetX && ty == targetY) {
                min = dist;
                break;
            }

            int nx = 0;
            int ny = 0;
            if (direction == 0) {
                for (int i = 0; i < 2; i++) {
                    nx = tx + dx[i];
                    ny = ty + dy[i];
                    if (!isRange(nx, ny)) continue;
                    if (visited[nx][ny][direction]) continue;
                    visited[nx][ny][direction] = true;
                    deque.add(new Node(nx, ny, dist + 1, direction == 0 ? 1 : 0));
                }
            } else {
                for (int i = 2; i < 4; i++) {
                    nx = tx + dx[i];
                    ny = ty + dy[i];
                    if (!isRange(nx, ny)) continue;
                    if (visited[nx][ny][direction]) continue;
                    visited[nx][ny][direction] = true;
                    deque.add(new Node(nx, ny, dist + 1, direction == 0 ? 1 : 0));
                }
            }

        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x > 200 || y < 0 || y > 200 ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
