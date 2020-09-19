package boj.boj10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N;
    static char[][] nomalMap;
    static char[][] abnormalMap;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        nomalMap = new char[N][N];
        abnormalMap = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                nomalMap[i][j] = input.charAt(j);
                if (nomalMap[i][j] == 'R') {
                    abnormalMap[i][j] = 'G';
                } else {
                    abnormalMap[i][j] = nomalMap[i][j];
                }
            }
        }

        int res1 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i , j, 0, nomalMap[i][j]);
                    res1++;
                }
            }
        }
        visited = new boolean[N][N];
        int res2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i , j, 1, abnormalMap[i][j]);
                    res2++;
                }
            }
        }
        System.out.println(res1 + " " + res2);

    }

    private static void bfs(int i, int j, int flag, char c) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        visited[i][j] = true;
        char comp = c;
        while (!q.isEmpty()) {
            Pair tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int d = 0; d < 4; d++) {
                int nx = tx + dx[d];
                int ny = ty + dy[d];
                if (nx >= N || nx < 0 || ny < 0 || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (flag == 0 && nomalMap[nx][ny] != c) continue;
                if (flag == 1 && abnormalMap[nx][ny] != c) continue;
                q.add(new Pair(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }

}
