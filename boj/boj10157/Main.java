package boj.boj10157;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int C,R, K, maxSize;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}; // 오른쪽, 아래, 왼쪽, 위
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = stoi(st.nextToken());
        R = stoi(st.nextToken());
        K = stoi(br.readLine());
        map = new int[C + 1][R + 1];
        maxSize = C * R;


        if (K > maxSize) {
            bw.write("0");
            bw.flush();
            System.exit(0);
        }

        int num = 1;
        int x = 1;
        int y = 0;
        int nx = 0;
        int ny = 0;
        int d = 0;
        while (num <= maxSize) {
            nx = x + dx[d];
            ny = y + dy[d];

            // 턴
            if (!isRange(nx, ny) || map[nx][ny] != 0) {
                nx -= dx[d];
                ny -= dy[d];
                d = turn(d);
                nx += dx[d];
                ny += dy[d];
            }

            // 이동
            map[nx][ny] = num++;
            if (map[nx][ny] == K) break;
            x = nx;
            y = ny;
        }
        bw.write(nx + " " + ny);
        bw.flush();
    }

    static int turn(int d) {
        if (d == 0) {
            return 1;
        } else if (d == 1) {
            return 2;
        } else if (d == 2) {
            return 3;
        }
        return 0;
    }

    static boolean isRange(int x, int y) {
        return x <= 0 || x > C || y <= 0 || y > R ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
