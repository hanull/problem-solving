package boj.boj2933;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R,C,N;
    static char[][] map;
    static boolean[][] visited;
    static int[] stick;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static ArrayList<Pair> cluster;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int t=0; t<N; t++) {
            destory(t); // 미네랄 파괴
            checkMineral();
            if (cluster.size() > 0)
                moveMineral();
        }
        print();
    }

    private static void moveMineral() {
        // 중력으로 몇 칸 이동할 수 있는지 체크
        int jump = 1;
        boolean flag = true;
        while (flag) {
            jump++;
            for (Pair tmp : cluster) {
                int nx = tmp.x+jump;
                int ny = tmp.y;
                if (!isRange(nx,ny) || map[nx][ny] == 'x') {
                    flag = false;
                    jump--;
                    break;
                }
            }
        }

        // 이동한 곳에 미네랄 입력
        for (Pair tmp : cluster) {
            int nx = tmp.x + jump;
            int ny = tmp.y;
            map[nx][ny] = 'x';
        }
    }

    private static void checkMineral() {
        visited = new boolean[R+1][C+1];
        cluster = new ArrayList<>();

        // 땅하고 연결 되어 있는 미네랄 체크
        for (int i=1; i<=C; i++) {
            if (visited[R][i] || map[R][i] == '.') continue;
            visited[R][i] = true;
            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(R, i));
            while (!q.isEmpty()) {
                Pair tmp = q.poll();
                int tx = tmp.x;
                int ty = tmp.y;
                for (int d=0; d<4; d++) {
                    int nx = tx + dx[d];
                    int ny = ty + dy[d];
                    if (!isRange(nx, ny)) continue;
                    if (map[nx][ny]=='.' || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    q.add(new Pair(nx,ny));
                }
            }
        }

        // 공중에 존재하는 미네랄 체크
        for (int i=1; i<=R; i++) {
            for (int j=1; j<=C; j++) {
                if (!visited[i][j] && map[i][j]=='x') {
                    cluster.add(new Pair(i,j));
                    map[i][j] = '.';
                }
            }
        }
    }

    private static boolean isRange(int nx, int ny) {
        if (nx <= 0 || nx > R || ny <= 0 || ny > C) return false;
        return true;
    }

    private static void destory(int n) {
        if (n%2 == 0) {
            for (int i = 1; i <= C; i++) {
                if (map[R - stick[n] + 1][i] == 'x') {
                    map[R - stick[n] + 1][i] = '.';
                    break;
                }
            }
        }
        else
        {
            for (int i=C; i>=1; i--) {
                if (map[R-stick[n]+1][i] == 'x') {
                    map[R-stick[n]+1][i] = '.';
                    break;
                }
            }
        }
    }

    private static void print() {
        for (int i=1; i<=R; i++) {
            for (int j=1; j<=C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        map = new char[R+1][C+1];
        for (int i=1; i<=R; i++) {
            String str = br.readLine();
            for (int j=1; j<=C; j++) {
                map[i][j] = str.charAt(j-1);
            }
        }
        N = stoi(br.readLine());
        stick = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) stick[i] = stoi(st.nextToken());
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Pair {
    int x,y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}