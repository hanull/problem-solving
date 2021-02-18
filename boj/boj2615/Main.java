package boj.boj2615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
< 놓친 부분 >
- 가장 왼쪽, 가장 위에 있는 바둑알을 출력해야한다!
 */

public class Main {

    static int[][] board = new int[20][20];
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, -1, 1, 1, -1, -1, 1};
    static ArrayList<Node> black = new ArrayList<>();
    static ArrayList<Node> white = new ArrayList<>();
    static class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 19; j++) {
                board[i][j] = stoi(st.nextToken());
                if (board[i][j] == 1) black.add(new Node(i, j, 0));
                else if (board[i][j] == 2) white.add(new Node(i, j, 0));
            }
        }

        checkWinner();
    }

    static void checkWinner() {
        // 검은돌
        visited = new boolean[20][20][8];
        for (Node node : black) {
            int x = node.x;
            int y = node.y;
            for (int i = 0; i < 8; i++) {
                if (visited[x][y][i]) continue;
                visited[x][y][i] = true;
                if (isWin(node, 1, i)) {
                    if (i == 0 || i == 2 || i == 5 || i == 6 || i == 7) {
                        x += dx[i] * 4;
                        y += dy[i] * 4;
                    }
                    System.out.println(1);
                    System.out.println(x + " " + y);
                    return;
                }

            }
        }

        // 흰 돌
        visited = new boolean[20][20][8];
        for (Node node : white) {
            int x = node.x;
            int y = node.y;
            for (int i = 0; i < 8; i++) {
                if (visited[x][y][i]) continue;
                visited[x][y][i] = true;
                if (isWin(node, 2, i)) {
                    if (i == 0 || i == 2 || i == 5 || i == 6 || i == 7) {
                        x += dx[i] * 4;
                        y += dy[i] * 4;
                    }
                    System.out.println(2);
                    System.out.println(x + " " + y);
                    return;
                }

            }
        }

        System.out.println(0);
    }

    static boolean isWin(Node node, int flag, int i) {
        int x = node.x + dx[i];
        int y = node.y + dy[i];
        int cnt = 1;
        while (isRange(x, y) && board[x][y] == flag) {
            visited[x][y][i] = true;
            cnt++;
            x += dx[i];
            y += dy[i];
        }
        if (cnt == 5) {
            int d = turn(i);
            x = node.x + dx[d];
            y = node.y + dy[d];
            if (isRange(x, y) && board[x][y] != flag) return true;
        }
        return false;
    }

    static int turn(int d) {
        int res = 5;
        switch (d) {
            case 0:
                res = 1;
                break;
            case 1:
                res = 0;
                break;
            case 2:
                res = 3;
                break;
            case 3:
                res = 2;
                break;
            case 4:
                res = 6;
                break;
            case 5:
                res = 7;
                break;
            case 6:
                res = 4;
                break;
        }
        return res;
    }

    static boolean isRange(int x, int y) {
        return x <= 0 || x > 19 || y <= 0 || y > 19 ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
