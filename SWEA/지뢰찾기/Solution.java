package SWEA.지뢰찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    static int N, answer;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static boolean[][] visited;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = stoi(br.readLine());
            map = new char[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.' && !visited[i][j] && isNoBomb(i, j)) {
                        go(i, j);
                        answer++;
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.' && !visited[i][j]) answer++;
                }
            }
            result.append("#").append(tc).append(" ").append(answer).append("\n");

        }
        System.out.print(result);
    }

    private static boolean isNoBomb(int x, int y) {
        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!isRange(nx ,ny)) continue;
            if (map[nx][ny] == '*') return false;
        }
        return true;
    }

    private static void go(int x, int y) {
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(x, y));
        visited[x][y] = true;

        while (!deque.isEmpty()) {
            Node tmp = deque.pollFirst();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int d = 0; d < 8; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == '*') continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if (isNoBomb(nx, ny)) deque.add(new Node(nx, ny));
            }
        }
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
