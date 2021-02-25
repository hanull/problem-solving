package boj.boj2999;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int len = input.length();
        int r = 0;
        int c = 0;
        for (int i = 1; i <= Math.sqrt(len); i++) {
            int tmp = len / i;
            if (tmp * i == len) {
                r = i;
                c = tmp;
            }
        }
        char[][] map = new char[100][100];
        int idx = 0;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                map[j][i] = input.charAt(idx++);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(map[i][j]);
            }
        }
        System.out.println(sb);
    }
}
