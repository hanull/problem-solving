package boj.boj2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static int[][] map;
    static boolean[][] visitied;
    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        visitied = new boolean[N][M];
        for (int i=0; i<N; i++){
            String str = br.readLine();
            for (int j=0; j<M; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }
        bfs(0,0);
    }

    private static void bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y, 1));
        visitied[x][y] = true;
        while (!q.isEmpty()) {
            Pair tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int dist = tmp.dist;
            if (tx==N-1 && ty==M-1) {
                System.out.println(dist);
                return;
            }
            for (int i=0; i<4; i++) {
                int nx = tx+dx[i];
                int ny = ty+dy[i];
                if (nx<0 || nx >= N || ny<0 || ny>=M) continue;
                if (visitied[nx][ny]) continue;
                if (map[nx][ny] == 0) continue;
                visitied[nx][ny] = true;
                q.add(new Pair(nx,ny,dist+1));
            }
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Pair{
    int x,y,dist;
    public Pair(int x, int y, int dist){
        this.x=x;
        this.y=y;
        this.dist=dist;
    }
}