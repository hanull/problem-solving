package boj.boj13460;

import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;
        int gx = 0;
        int gy = 0;
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '#' || map[i][j] == '.') {
                    continue;
                }
                if (map[i][j] == 'B') {
                    bx = i;
                    by = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'R') {
                    rx = i;
                    ry = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'O') {
                    gx = i;
                    gy = j;
                }
            }
        }

        boolean[][][][] visited = new boolean[N][M][N][M];
        for (int direction = 0; direction < 4; direction++) {
            play(rx, ry, bx, by, gx, gy, 0, direction, map, visited);
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void play(final int rx, final int ry, final int bx, final int by, final int gx, final int gy,
                             final int count, final int direction, final char[][] map, final boolean[][][][] visited) {
        if (count > 10 || count > answer) {
            return;
        }

        // 빨간생 공이 구멍에 들어가고 파랑색은 들어가지 않은 경우, 종료
        if (rx == gx && ry == gy && (rx != bx || ry != by)) {
            answer = count;
            return;
        }

        int nrx = rx;
        int nry = ry;
        int nbx = bx;
        int nby = by;
        if (canRedMoveFirst(nrx, nry, nbx, nby, direction)) {
            // 빨간색 공 이동
            while (true) {
                if (map[nrx][nry] == 'O') {
                    break;
                }
                if (map[nrx][nry] == '#') {
                    nrx -= dx[direction];
                    nry -= dy[direction];
                    break;
                }
                nrx += dx[direction];
                nry += dy[direction];
            }

            // 파란색 공 이동
            while (true) {
                // 파란색 공이 구멍에 들어가면 종료
                if (map[nbx][nby] == 'O') {
                    return;
                }
                // 벽이나 빨간색 공을 만난 경우
                if (map[nbx][nby] == '#' || nbx == nrx && nby == nry) {
                    nbx -= dx[direction];
                    nby -= dy[direction];
                    break;
                }
                nbx += dx[direction];
                nby += dy[direction];
            }
        } else {
            // 파란색 공 이동
            while (true) {
                // 파란색 공이 구멍에 들어가면 종료
                if (map[nbx][nby] == 'O') {
                    return;
                }
                if (map[nbx][nby] == '#') {
                    nbx -= dx[direction];
                    nby -= dy[direction];
                    break;
                }
                nbx += dx[direction];
                nby += dy[direction];
            }

            // 빨간색 공 이동
            while (true) {
                if (map[nrx][nry] == 'O') {
                    break;
                }
                if (map[nrx][nry] == '#' || nrx == nbx && nry == nby) {
                    nrx -= dx[direction];
                    nry -= dy[direction];
                    break;
                }
                nrx += dx[direction];
                nry += dy[direction];
            }
        }

        for (int nextDirection = 0; nextDirection < 4; nextDirection++) {
            if (!visited[nrx][nry][nbx][nby]) {
                visited[nrx][nry][nbx][nby] = true;
                play(nrx, nry, nbx, nby, gx, gy, count + 1, nextDirection, map, visited);
                visited[nrx][nry][nbx][nby] = false;
            }
        }
    }

    private static boolean canRedMoveFirst(final int rx, final int ry, final int bx, final int by,
                                           final int direction) {
        // 상,하,좌,우 순서
        if (direction == 0) {
            return rx < bx || ry != by;
        } else if (direction == 1) {
            return rx > bx || ry != by;
        } else if (direction == 2) {
            return rx != bx || ry < by;
        } else {
            return rx != bx || ry > by;
        }
    }
}
