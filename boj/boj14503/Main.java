package boj.boj14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int x,y,d;
    public Pair(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
public class Main {

    static int N, M;
    static int cnt = 1;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};    // 북, 동, 남, 서
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        st = new StringTokenizer(br.readLine());
        int x = stoi(st.nextToken());
        int y = stoi(st.nextToken());
        int dir = stoi(st.nextToken());
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        solve(x, y, dir);
        System.out.println(cnt);
    }

    private static void solve(int x, int y, int dir) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y,dir));
        visited[x][y] = true;
        map[x][y] = -1;

        while (!q.isEmpty()) {
            Pair tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int td = tmp.d;
            boolean flag = false;

            int nd = td;
            for (int i=0; i<4; i++) {
                nd = turn(nd, 0);
                int nx = tx+dx[nd];
                int ny = ty+dy[nd];
                if (nx < 0 || nx >= N) continue;
                if (ny < 0 || ny >= M) continue;
                if (map[nx][ny] == 1 || map[nx][ny] == -1) continue;
                if (visited[nx][ny]) continue;
                map[nx][ny] = -1;
                visited[nx][ny] = true;
                cnt++;
                flag = true;
                q.add(new Pair(nx,ny,nd));
                break;
            }
            if (!flag)
            {
                nd = turn(td, 1);
                int nx = tx+dx[nd];
                int ny = ty+dy[nd];
                if (nx < 0 || nx >= N) continue;
                if (ny < 0 || ny >= M) continue;
                if (map[nx][ny] == 1) continue;
                q.add(new Pair(nx, ny, td));
            }
        }
    }

    private static int turn(int dir, int flag) {
        int res = 0;
        switch (flag) {
            case 0:
                if (dir == 0) res = 3;
                else if (dir == 1) res = 0;
                else if (dir == 2) res = 1;
                else res = 2;
                break;
            case 1:
                if (dir == 0) res = 2;
                else if (dir == 1) res = 3;
                else if (dir == 2) res = 0;
                else res = 1;
                break;
        }

        return res;
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
