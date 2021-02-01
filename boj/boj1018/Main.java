package boj.boj1018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] board;
    static int min = 64;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int r = 0; r < N - 7; r++) {
            for (int c = 0; c < M - 7; c++) {
                paint(r, c);
            }
        }
        System.out.println(min);
    }

    static void paint(int r, int c) {
        // 흰색 시작
        int cnt = 0;
        char[][] tmp = copyBoard(r, c);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        if (tmp[i][j] != 'W') cnt++;
                    } else {
                        if (tmp[i][j] != 'B') cnt++;
                    }
                } else {
                    if (j % 2 == 0) {
                        if (tmp[i][j] != 'B') cnt++;
                    } else {
                        if (tmp[i][j] != 'W') cnt++;
                    }
                }
            }
        }
        min = Math.min(cnt, min);

        // 검정 시작
        cnt = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        if (tmp[i][j] != 'B') cnt++;
                    } else {
                        if (tmp[i][j] != 'W') cnt++;
                    }
                } else {
                    if (j % 2 == 0) {
                        if (tmp[i][j] != 'W') cnt++;
                    } else {
                        if (tmp[i][j] != 'B') cnt++;
                    }
                }
            }
        }
        min = Math.min(min, cnt);
    }

    static char[][] copyBoard(int r, int c) {
        char[][] res = new char[8][8];
        for (int i = r; i < r + 8; i++) {
            for (int j = c; j < c + 8; j++) {
                res[i - r][j - c] = board[i][j];
            }
        }
        return res;
    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
