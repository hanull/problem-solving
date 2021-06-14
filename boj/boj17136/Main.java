package boj.boj17136;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 100;
    static int[][] map = new int[10][10];
    static int[] paperArray = new int[6];
    static int minPaperCount = MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(minPaperCount == MAX ? -1 : minPaperCount);
    }

    private static void dfs(int x, int y) {
        if (y >= 10) {
            dfs(x + 1, 0);
            return;
        }
        if (x >= 10) {
            if (checkMap()) minPaperCount = Math.min(minPaperCount, totalPaperCount());
            return;
        }
        if (map[x][y] == 0) {
            dfs(x, y + 1);
            return;
        }
        for (int i = 5; i >= 1; i--) {
            if (!isPossible(x, y, i) || paperArray[i] >= 5) continue;
            setMap(x, y, i, 0);
            dfs(x, y + i);
            setMap(x, y, i, 1);
        }
    }

    private static void setMap(int x, int y, int range, int number) {
        for (int i = x; i < x + range; i++) {
            for (int j = y; j < y + range; j++) {
                map[i][j] = number;
            }
        }
        if (number == 0) paperArray[range]++;
        else paperArray[range]--;
    }

    private static boolean isPossible(int x, int y, int range) {
        for (int i = x; i < x + range; i++) {
            for (int j = y; j < y + range; j++) {
                if (!isRange(i, j)) return false;
                if (map[i][j] == 0) return false;
            }
        }
        return true;
    }

    private static boolean isRange(int x, int y) {
        return x < 0 || x >= 10 || y < 0 || y >= 10 ? false : true;
    }

    private static int totalPaperCount() {
        int total = 0;
        for (int i = 1; i <= 5; i++) {
            total += paperArray[i];
        }
        return total;
    }

    private static boolean checkMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == 1) return false;
            }
        }
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
