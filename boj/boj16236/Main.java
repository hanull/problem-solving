package boj.boj16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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
            return Integer.compare(this.y, o.y);
        }
        return Integer.compare(this.dist, o.dist);
    }
}

public class Main {

    static int N, sharkX, sharkY;
    static int sharkSize = 2;
    static int[][] map;

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
        bfs(sharkX, sharkY);
    }

    private static void bfs(int sharkX, int sharkY) {
        Queue<Shark> q = new LinkedList<>();
        q.add(new Shark(sharkX, sharkY, 2, 0));

    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}
