package SWEA.정사각형방;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int T, N;
    static int[][] room;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int roomNum, moveCnt;

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        T = stoi(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            roomNum = moveCnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int cnt = move(i, j);
                    if (cnt > moveCnt) {
                        moveCnt = cnt;
                        roomNum = room[i][j];
                    } else if (cnt == moveCnt) {
                        roomNum = Math.min(roomNum, room[i][j]);
                    }
                }
            }
            System.out.println("#" + tc + " " + roomNum + " " + moveCnt);
        }

    }

    static int move(int x, int y) {
        int res = 1;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));

        while (!q.isEmpty()) {
            Node tmp = q.poll();
            int tx = tmp.x;
            int ty = tmp.y;
            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (!isRange(nx, ny)) continue;
                if (room[tx][ty] + 1 == room[nx][ny]) {
                    q.add(new Node(nx, ny));
                    res++;
                }
            }
        }
        return res;
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N ? false : true;
    }

    static void init() throws IOException {
        N = stoi(br.readLine());
        room = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                room[i][j] = stoi(st.nextToken());
            }
        }
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
