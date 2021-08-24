package boj.boj16954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static char[][] map = new char[8][8];
    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {0,-1,1,0,0,-1,-1,1,1};
    static int[] dy = {0,0,0,-1,1,-1,1,-1,1};
    static int wall;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0; i<8; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j=0; j<8; j++) {
                if (map[i][j] == '#') wall++;
            }
        }
        System.out.println(wall == 0 || isPossible() ? 1 : 0);
    }

    private static boolean isPossible() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(7, 0));
        while (!q.isEmpty()) {
            if (wall == 0) return true;
            int len = q.size();
            for (int i=0; i<len; i++) {
                Node node = q.poll();
                int tx = node.x;
                int ty = node.y;
                if (tx == 0 && ty == 7) return true;
                if (map[tx][ty] == '#') continue;
                for (int d=0; d<9; d++) {
                    int nx = tx+dx[d];
                    int ny = ty+dy[d];
                    if (!isRange(nx, ny)) continue;
                    if (map[nx][ny] == '#') continue;
                    q.add(new Node(nx, ny));
                }
            }
            // 벽 이동
            for (int i=0; i<8; i++) {
                if (wall == 0) break;
                if (map[7][i] == '#') {
                    map[7][i] = '.';
                    wall--;
                }
                for (int j=6; j>=0; j--) {
                    if (map[j][i] == '#') {
                        map[j][i] = '.';
                        map[j + 1][i] = '#';
                    }
                }
            }

        }
        return false;
    }

    private static boolean isRange(int nx, int ny) {
        return nx < 0 || nx >= 8 || ny < 0 || ny >= 8 ? false : true;
    }
}
