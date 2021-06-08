package boj.슈퍼베리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y, dist, superberry;

        public Node(int x, int y, int dist, int superberry) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.superberry = superberry;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = findSuperBerry();
        System.out.println(answer == -1 ? "SORRY" : answer);
    }

    private static int findSuperBerry() {
        int answer = -1;
        Queue<Node> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];
        if (map[0][0] == 'S') {
            queue.add(new Node(0, 0, 0, 1));
            visited[0][0][1] = true;
        } else {
            queue.add(new Node(0, 0, 0, 0));
            visited[0][0][0] = true;
        }
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int dist = tmp.dist;
            int superberry = tmp.superberry;
            if (superberry == 1 && map[tx][ty] == 'G') {
                answer = dist;
                break;
            }
            if (map[tx][ty] == 'S') superberry = 1;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == 'M') continue;
                if (visited[nx][ny][superberry]) continue;
                visited[nx][ny][superberry] = true;
                queue.add(new Node(nx, ny, dist + 1, superberry));
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
