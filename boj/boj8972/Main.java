package boj.boj8972;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean flag;
    static char[][] map;
    static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    static char[] direction;
    static Node jongsu;
    static ArrayList<Node> arduinoList = new ArrayList<>();
    static int[][] explosion;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new char[N][M];
        explosion = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'I') jongsu = new Node(i, j);
                else if (map[i][j] == 'R') arduinoList.add(new Node(i, j));
            }
        }
        String d = br.readLine();
        direction = new char[d.length()];
        direction = d.toCharArray();

        int moveCount = move();
        if (flag) {
            print();
        } else {
            System.out.println("kraj " + moveCount);
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int move() {
        int count = 0;
        flag = true;
        for (int idx = 0; idx < direction.length; idx++) {
            count++;
            int dir = direction[idx] - '0';
            // 종수 이동
            int x = jongsu.x + dx[dir];
            int y = jongsu.y + dy[dir];
            // 종수가 미친 아두이노 만나면 종료
            if (map[x][y] == 'R') {
                flag = false;
                return count;
            }
            map[jongsu.x][jongsu.y] = '.';
            jongsu.x = x;
            jongsu.y = y;
            map[x][y] = 'I';

            for (int i = 0; i < N; i++) {
                Arrays.fill(explosion[i], 0);
            }

            if (!moveArduino()) {
                return count;
            }

            explode();
        }

        return count;
    }

    static void explode() {
        // 같은 칸에 2개 이상 미친 아두노이가 존재할 때 폭발!
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R' && explosion[i][j] > 1) {
                    map[i][j] = '.';
                    Iterator<Node> iterator = arduinoList.iterator();
                    while (iterator.hasNext()) {
                        Node tmp = iterator.next();
                        int a = tmp.x;
                        int b = tmp.y;
                        if (a == i && b == j) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
    }

    static boolean moveArduino() {
        // 미친 아두이노 이동
        for (Node arduino : arduinoList) {
            int tx = arduino.x;
            int ty = arduino.y;
            if (explosion[tx][ty] == 0) map[tx][ty] = '.';

            // 이동할 방향 결정
            int minDist = Integer.MAX_VALUE;
            int d = 0;
            for (int i = 1; i <= 9; i++) {
                if (i == 5) continue;
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                int dist = Math.abs(jongsu.x - nx) + Math.abs(jongsu.y - ny);
                if (dist < minDist) {
                    minDist = dist;
                    d = i;
                }
            }
            int nx = tx + dx[d];
            int ny = ty + dy[d];
            // 종수 사망
            if (map[nx][ny] == 'I') {
                flag = false;
                return false;
            }
            arduino.x = nx;
            arduino.y = ny;
            map[nx][ny] = 'R';
            explosion[nx][ny] += 1;
        }

        return true;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
