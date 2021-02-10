package boj.boj2563;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[101][101];
        int N = stoi(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            for (int r = x; r < x + 10; r++) {
                for (int c = y; c < y + 10; c++) {
                    map[r][c] = 1;
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if (map[i][j] == 1) cnt++;
            }
        }
        System.out.println(cnt);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
