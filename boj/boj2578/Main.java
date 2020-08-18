package boj.boj2578;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[5][5];
        HashMap<Integer, Pair> hm = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(str[j]);
                hm.put(map[i][j], new Pair(i, j));
            }
        }
        int res = 0;
        for (int i = 0; i < 5; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                res++;
                int callNum = Integer.parseInt(str[j]);
                Pair tmp = hm.get(callNum);
                map[tmp.x][tmp.y] = 0;
                if (isPossible(map)) {
                    System.out.println(res);
                    return;
                }
            }
        }
    }

    private static boolean isPossible(int[][] map) {
        int res = 0;
        // 가로
        for (int i = 0; i < 5; i++) {
            boolean flag = true;
            for (int j = 0; j < 5; j++) {
                if (map[i][j] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) res++;
        }

        // 세로
        for (int i = 0; i < 5; i++) {
            boolean flag = true;
            for (int j = 0; j < 5; j++) {
                if (map[j][i] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) res++;
        }

        // 대각선
        boolean flag = true;
        for (int i = 0; i < 5; i++) {
            if (map[i][i] != 0) {
                flag = false;
                break;
            }
        }
        if (flag) res++;
        flag = true;
        for (int i = 0; i < 5; i++) {
            if (map[i][4-i] != 0) {
                flag = false;
                break;
            }
        }
        if (flag) res++;
        return res < 3 ? false : true;
    }
}
