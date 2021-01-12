package boj.boj7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H;
    static int[][][] map;
    static boolean[][][] visited;
    static int[] dx = {-1,1,0,0,0,0};
    static int[] dy = {0,0,1,-1,0,0};
    static int[] dz = {0,0,0,0,1,-1};
    static Queue<Node> tomato = new LinkedList<>();
    static int tomatoCnt = 0;
    static int totalDay = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        H = stoi(st.nextToken());
        map = new int[N][M][H];
        visited = new boolean[N][M][H];

        for (int z = 0; z < H; z++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int input = stoi(st.nextToken());
                    map[i][j][z] = input;
                    if (map[i][j][z] == 1) tomato.add(new Node(i, j, z, 0));
                    if (map[i][j][z] == 0) tomatoCnt++;
                }
            }
        }

        int cnt = bfs();
        if (cnt == tomatoCnt) {
            System.out.println(totalDay);
        } else {
            System.out.println(-1);
        }

    }

    private static int bfs() {
        int res = 0;

        while (!tomato.isEmpty()) {
            Node tmp = tomato.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            int tz = tmp.z;
            int time = tmp.time;
            totalDay = time;
            for (int i = 0; i < 6; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                int nz = tz + dz[i];
                if (!isRange(nx,ny,nz)) continue;
                if (visited[nx][ny][nz]) continue;
                if (map[nx][ny][nz] == -1 || map[nx][ny][nz] == 1) continue;
                visited[nx][ny][nz] = true;
                tomato.add(new Node(nx, ny, nz, time + 1));
                res++;
            }
        }
        return res;
    }

    private static boolean isRange(int x, int y, int z) {
        return x < 0 || x >= N || y < 0 || y >= M || z < 0 || z >= H ? false : true;
    }

    public static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Node {
    int x, y, z, time;

    public Node(int x, int y, int z, int time) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.time = time;
    }
}