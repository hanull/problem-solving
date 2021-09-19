package boj.boj17129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static char[][] map;
    static class Node {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new char[N][M];
        int x = 0, y = 0;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == '2') {
                    x = i;
                    y = j;
                    map[i][j] = '0';
                }
            }
        }
        int answer = bfs(x, y);
        if (answer == 0) {
            System.out.println("NIE");
        } else {
            System.out.println("TAK");
            System.out.println(answer);
        }
    }

    private static int bfs(int x, int y) {
        int answer = 0;
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new Node(x, y, 0));
        visited[x][y] = true;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int tx = node.x;
            int ty = node.y;
            int dist = node.dist;
            if (map[tx][ty] == '3' || map[tx][ty] == '4' || map[tx][ty] == '5') {
                answer = dist;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == '1') continue;
                visited[nx][ny] = true;
                q.add(new Node(nx, ny, dist + 1));
            }
        }
        return answer;
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= M ? false : true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
