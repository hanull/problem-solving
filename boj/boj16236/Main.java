package boj.boj16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Shark implements Comparable<Shark>{
    int x, y, size, dist;

    public Shark(int x, int y, int size, int dist) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.dist = dist;
    }

    @Override
    public int compareTo(Shark o) {
        if (this.dist == o.dist) {
            if (this.x == o.x) {
                return Integer.compare(this.y, o.y);
            }
            return Integer.compare(this.x, o.x);
        }
        return Integer.compare(this.dist, o.dist);
    }
}

public class Main {

    static int N, sharkX, sharkY;
    static int sharkSize = 2;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] == 9){
                    sharkX = i;
                    sharkY = j;
                }
            }
        }
        while (true) {
            bfs(sharkX, sharkY);    // 가장 가까운 물고리 위치 찾기


        }
    }

    private static void bfs(int sharkX, int sharkY) {

    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
