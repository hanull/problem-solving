package boj.boj15653;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {-1,1,0,0};
    static int goalX, goalY;
    static Pair startRed = null, startBlue = null;

    public static void main(String[] args) throws IOException {
        init();
        solve(startRed, startBlue);
    }

    private static void solve(Pair a, Pair b) {
        Queue<Ball> q = new LinkedList<>();
        q.add(new Ball(a, b, 0));
        visited[a.x][a.y][b.x][b.y] = true;
        while (!q.isEmpty()) {
            Ball tmp = q.poll();
            Pair red = tmp.red;
            Pair blue = tmp.blue;
            int cnt = tmp.cnt;
            for (int i=0; i<4; i++) {
                Pair nextRed = move(red, i);
                Pair nextBlue = move(blue, i);
                if (nextBlue.x==goalX && nextBlue.y==goalY) continue;
                if(nextRed.x==goalX && nextRed.y==goalY) {
                    System.out.println(cnt + 1);
                    return;
                }
                if (diff(nextRed, nextBlue)) {  // 이동 후 같은 위치에 존재할 때, 위치 조정
                    int redMoveCount = Math.abs(red.x-nextRed.x) + Math.abs(red.y-nextRed.y);
                    int blueMoveCount = Math.abs(blue.x-nextBlue.x) + Math.abs(blue.y-nextBlue.y);
                    if (redMoveCount > blueMoveCount) {
                        nextRed.x -= dx[i];
                        nextRed.y -= dy[i];
                    } else {
                        nextBlue.x -= dx[i];
                        nextBlue.y -= dy[i];
                    }
                }
                if (visited[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y]) continue;
                visited[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y] = true;
                q.add(new Ball(nextRed, nextBlue, cnt + 1));
            }
        }
        System.out.println("-1");
    }

    private static boolean diff(Pair nextRed, Pair nextBlue) {
        return (nextRed.x==nextBlue.x && nextRed.y==nextBlue.y) ? true : false;
    }

    private static Pair move(Pair tmp, int d) {
        int x = tmp.x;
        int y = tmp.y;
        while (map[x][y] != 'O' && map[x+dx[d]][y+dy[d]] != '#') {
            x+=dx[d];
            y+=dy[d];
        }
        return new Pair(x,y);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        for (int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                if (map[i][j] == 'R') {
                    startRed = new Pair(i,j);
                    map[i][j] = '.';
                }
                if (map[i][j] == 'B') {
                    startBlue = new Pair(i,j);
                    map[i][j] = '.';
                }
                if (map[i][j] == 'O') {
                    goalX = i;
                    goalY = j;
                }
            }
        }
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Ball {
    Pair red, blue;
    int cnt;
    public Ball(Pair red, Pair blue, int cnt) {
        this.red = red;
        this.blue = blue;
        this.cnt = cnt;
    }
}

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}