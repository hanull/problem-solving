package boj.boj3184;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static int sheepTotal = 0;
    static int wolfTotal = 0;
    static boolean[][] visited;
    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if (map[i][j]=='v' || map[i][j]=='o' && !visited[i][j]) {
                    bfs(i,j);
                }
            }
        }
        System.out.println(sheepTotal + " " + wolfTotal);
    }

    private static void getWinner(int sheep, int wolf) {
        if (wolf >= sheep) sheepTotal -= sheep;
        else wolfTotal -= wolf;
    }

    private static void bfs(int x, int y) {
        int sheep = 0;
        int wolf = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));
        visited[x][y] = true;
        while (!q.isEmpty()) {
            Pair tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            if (map[tx][ty] == 'o') sheep++;
            if (map[tx][ty] == 'v') wolf++;
            for (int i=0; i<4; i++) {
                int nx = tx+dx[i];
                int ny = ty+dy[i];
                if (!isRange(nx, ny)) continue;
                if (visited[nx][ny] || map[nx][ny] == '#') continue;
                visited[nx][ny] = true;
                q.add(new Pair(nx,ny));
            }
        }
        getWinner(sheep, wolf);
    }

    private static boolean isRange(int x, int y) {
        if (x<0 || x>=R || y<0 || y>=C) return false;
        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i=0; i<R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j=0; j<C; j++){
                if (map[i][j] == 'o') sheepTotal++;
                if (map[i][j] == 'v') wolfTotal++;
            }
        }
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