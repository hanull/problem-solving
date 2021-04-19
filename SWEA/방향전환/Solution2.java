package SWEA.방향전환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution2 {

    static int startX, startY, endX, endY, startD, endD;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y, count, direction;

        public Node(int x, int y, int count, int direction) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            startX = stoi(st.nextToken()) + 100;
            startY = stoi(st.nextToken()) + 100;
            endX = stoi(st.nextToken()) + 100;
            endY = stoi(st.nextToken()) + 100;
            result.append("#").append(tc).append(" ").append(move()).append("\n");
        }
        System.out.print(result);
    }

    static int move() {
        int result = 0;
        Deque<Node> deque = new ArrayDeque<>();
        boolean[][][] visited = new boolean[201][201][4];
        deque.add(new Node(startX, startY, 0, 0));
        visited[startX][startY][0] = true;

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            int count = tmp.count;
            turn(tmp.direction);
            if (tx == endX && ty == endY) {
                result = count;
                break;
            }
            for (int d = startD; d <= endD; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny][d]) continue;
                visited[nx][ny][d] = true;
                if (d == 0 || d == 1) {
                    deque.add(new Node(nx, ny, count + 1, 1));
                } else {
                    deque.add(new Node(nx, ny, count + 1, 2));
                }
            }
        }
        return result;
    }

    static void turn(int direction) {
        if (direction == 0) {
            startD = 0;
            endD = 3;
        }
        else if (direction == 1) {  // 상,하 -> 좌,우
            startD = 2;
            endD = 3;
        }
        else if (direction == 2) {  // 좌,우 -> 상,하
            startD = 0;
            endD = 1;
        }
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x > 200 || y < 0 || y > 200 ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
