package boj.boj1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        searchBoard(0, 0, new HashSet<>());
        System.out.println(max);
    }

    static void searchBoard(int x, int y, HashSet<Character> checkSet) {
        checkSet.add(board[x][y]);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isRange(nx, ny)) continue;
            if (!checkSet.contains(board[nx][ny])) {
                searchBoard(nx, ny, checkSet);
            }
        }
        max = Math.max(max, checkSet.size());
        checkSet.remove(board[x][y]);
    }

    static boolean isRange(int x, int y) {
        return x < 0 || x >= R || y < 0 || y >= C ? false : true;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
