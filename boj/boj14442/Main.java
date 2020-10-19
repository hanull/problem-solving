package boj.boj14442;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M,K;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][K+1];
        for (int i=0; i<N; i++){
            String str = br.readLine();
            for (int j=0; j<M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        bfs(0,0);
    }

    private static void bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y,0,1));
        visited[x][y][0] = true;
        while (!q.isEmpty()){
            Pair tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int cnt = tmp.cnt;
            int d = tmp.d;
            if (tx==N-1 && ty==M-1) {
                System.out.println(d);
                return;
            }
            for (int i=0; i<4; i++) {
                int nx = tx+dx[i];
                int ny = ty+dy[i];
                if (!isRange(nx, ny)) continue;
                if (map[nx][ny] == 0 && visited[nx][ny][cnt]) continue;
                if (map[nx][ny] == 1) {
                    if (cnt >= K) continue;
                    if (visited[nx][ny][cnt+1]) continue;
                    visited[nx][ny][cnt+1] = true;
                    q.add(new Pair(nx,ny,cnt+1, d+1));
                } else {
                    visited[nx][ny][cnt] = true;
                    q.add(new Pair(nx,ny,cnt,d+1));
                }
            }

        }
        System.out.println("-1");
    }

    private static boolean isRange(int x, int y) {
        if (x<0 || x>=N || y<0 || y >=M) return false;
        return true;
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Pair{
    int x,y,cnt,d;
    public Pair(int x, int y,int cnt, int d) {
        this.x=x;
        this.y=y;
        this.cnt=cnt;
        this.d=d;
    }
}