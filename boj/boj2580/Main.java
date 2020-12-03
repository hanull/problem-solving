package boj.boj2580;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[][] map = new int[9][9];
    static ArrayList<Pair> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] == 0) list.add(new Pair(i, j));
            }
        }
        dfs(0);
    }

    private static void dfs(int cnt) {
        if (cnt == list.size()) {
            print();
            System.exit(0);
        }
        for (int i = 1; i <= 9; i++) {
            Pair tmp = list.get(cnt);
            if (!isPossible(tmp, i)) continue;
            map[tmp.x][tmp.y] = i;
            dfs(cnt+1);
            map[tmp.x][tmp.y] = 0;
        }
    }

    private static boolean isPossible(Pair tmp, int num) {
        int x = tmp.x;
        int y = tmp.y;
        for (int i = 0; i < 9; i++) {
            if (i == y) continue;
            if (map[x][i] == num) return false;
        }
        for (int i = 0; i < 9; i++) {
            if (i == x) continue;
            if (map[i][y] == num) return false;
        }
        int r = x /3 * 3;
        int c = y / 3 * 3;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (r == x && c == y) continue;
                if (map[i][j] == num) return false;
            }
        }
        return true;
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int stoi(String input) {
        return Integer.valueOf(input);
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}