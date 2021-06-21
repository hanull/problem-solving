package boj.boj18808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        map = new int[N][M];
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = stoi(st.nextToken());
            int m = stoi(st.nextToken());
            int[][] stickerA = new int[n][m];
            int[][] stickerB = new int[m][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    stickerA[i][j] = stoi(st.nextToken());
                }
            }
            for (int i = 0; i < 4; i++) {
                if (i % 2 == 0) {
                    if (attachSticker(stickerA, n, m))  break;
                    turnSticker(stickerA, stickerB);
                } else {
                    if (attachSticker(stickerB, m, n))  break;
                    turnSticker(stickerB, stickerA);
                }
            }
        }
        System.out.println(calculateTotalSticker());
    }

    private static int calculateTotalSticker() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) total++;
            }
        }
        return total;
    }

    private static void turnSticker(int[][] baseArray, int[][] turnArray) {
        for (int i = 0; i < baseArray[0].length; i++) {
            for (int j = 0; j < baseArray.length; j++) {
                turnArray[i][j] = baseArray[baseArray.length - j - 1][i];
            }
        }
    }

    private static boolean attachSticker(int[][] sticker, int n, int m) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i + n > N) return false;
                if (j + m > M) break;
                boolean flag = true;
                for (int r = 0; r < n; r++) {
                    int nr = i + r;
                    for (int c = 0; c < m; c++) {
                        int nc = j + c;
                        if (sticker[r][c] == 1 && map[nr][nc] == 1) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) break;
                }
                if (flag) {
                    for (int r = 0; r < n; r++) {
                        int nr = i + r;
                        for (int c = 0; c < m; c++) {
                            int nc = j + c;
                            if (sticker[r][c] == 1) map[nr][nc] = 1;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
